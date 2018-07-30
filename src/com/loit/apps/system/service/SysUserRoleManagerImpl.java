package com.loit.apps.system.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.system.model.SysRoleModel;
import com.loit.apps.system.model.SysUserModel;
import com.loit.apps.system.model.SysUserRoleModel;
import com.loit.core.commom.SysConfig;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;

@Service
public class SysUserRoleManagerImpl extends CommonManager {

	@Autowired
	SysUserManagerImpl sysUserManager;

	@Autowired
	SysLogManagerImpl sysLogManager;

	@Autowired
	SysRoleManagerImpl sysRoleManager;
	

	public SysUserRoleModel get(String id) {
		return this.dao.get(SysUserRoleModel.class, id);
	}

	public List<SysUserRoleModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysUserRoleModel.class, orderBy, pagingInfo);
	}

	public List<SysUserRoleModel> findByExample(SysUserRoleModel example, String orderBy, PagingInfo pagingInfo) {
		return this.dao.findByExample(example, orderBy, pagingInfo);
	}

	public SysUserRoleModel save(SysUserRoleModel model) {
		model = this.dao.save(model);
		return model;
	}

	public List<SysUserRoleModel> saveAll(Collection<SysUserRoleModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysUserRoleModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysUserRoleModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		SysUserRoleModel model = this.get(id);
		this.dao.remove(model);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysUserRoleModel.class, ids);
	}

	// TODO 这个方法需要修改！！！
	public boolean checkIfAdmin(String userId) {
		SysUserRoleModel wlUserRoleModel = new SysUserRoleModel();
		wlUserRoleModel.setUserId(userId);
		List<SysUserRoleModel> modeList = this.dao.findByExample(wlUserRoleModel, null, null);
		for (int i = 0; i < modeList.size(); i++) {
			SysUserRoleModel model = (SysUserRoleModel) modeList.get(i);
			String roleId = model.getRoleId();
			if (roleId.equals("e1bf71ba-70c5-4325-b3e0-c492800e28e7")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取在某一角色下的用户
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysUserModel> getUsersByRoleId(String roleId) {
		List<SysUserModel> userList = new ArrayList<SysUserModel>();
		SysUserModel userModel = null;
		SysUserRoleModel example = new SysUserRoleModel();
		example.setRoleId(roleId);
		List<SysUserRoleModel> list = this.findByExample(example, null, null);
		if (list.size() == 0) {
			return null;
		}
		for (SysUserRoleModel model : list) {
			if (model.getUserId() == null || "".equals(model.getUserId())) {
				continue;
			}
			userModel = sysUserManager.get(model.getUserId());
			if (userModel != null) {
				if ("U".equals(userModel.getState())) {
					userList.add(userModel);
				}
			}
		}
		return userList;
	}
	
	/**
	 * 获取在某一用户的角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysRoleModel> getRoleByUserId(String userId) {
		List<SysRoleModel> roleList = new ArrayList<SysRoleModel>();
		SysRoleModel roleModel = null;
		SysUserRoleModel example = new SysUserRoleModel();
		example.setUserId(userId);
		List<SysUserRoleModel> list = this.findByExample(example, null, null);
		if (list.size() == 0) {
			return null;
		}
		for (SysUserRoleModel model : list) {
			if (model.getRoleId() == null || "".equals(model.getRoleId())) {
				continue;
			}
			roleModel = sysRoleManager.get(model.getRoleId());
			if (roleModel != null) {
				roleList.add(roleModel);
			}
		}
		return roleList;
	}
	
	/**
	 * 获取在某一用户的角色权限
	 * ("person", 10);
	 * ("manager", 20);
	 * ("groupLeader", 30);
	 * ("leader", 40);
	 * ("Admin", 0);
	 * @param userId
	 * @return
	 */
	public int getRolePermissions(String userId) {
		List<SysRoleModel> roleList = this.getRoleByUserId(userId);
		Map<String,Integer> roleMap = new HashMap<String,Integer>();
		roleMap.put("person", 10);
		roleMap.put("manager", 20);
		roleMap.put("groupLeader", 30);
		roleMap.put("leader", 40);
		roleMap.put("Admin", 0);
		int n = 0;
		for(SysRoleModel role:roleList){
			int code = roleMap.get(role.getCode());
			if(code > n) n = code;
		}
		return n;
	}

	public void saveUserRoles(String userId, String roles) {
		roles = roles.substring(0, roles.length() - 1);
		String[] rolesArray = roles.split(",");
		for (int i = 0; i < rolesArray.length; i++) {
			SysUserRoleModel userRoleModel = new SysUserRoleModel();
			userRoleModel.setUserId(userId);
			userRoleModel.setRoleId(rolesArray[i]);
			this.save(userRoleModel);
			SysRoleModel roleModel = new SysRoleModel();
			roleModel.setRoleId(userRoleModel.getRoleId());
			roleModel = this.dao.findByExample(roleModel).get(0);
			SysUserModel userModel = null;
			userModel = this.sysUserManager.get(userId);
			this.sysLogManager.saveSysLog("user", "授予用户角色", 1, "授予[" + userModel.getName() + "]用户[" + roleModel.getName() + "]角色", "授权成功", null);
			// sysLogManager.saveSysLog("user", "用户角色授权", 1, "授权成功", "授权成功", null);
		}
	}

	public void removeUserRoles(String userRoleIds) {
		userRoleIds = userRoleIds.substring(0, userRoleIds.length() - 1);
		String[] userRoleIdsArray = userRoleIds.split(",");
		for (int i = 0; i < userRoleIdsArray.length; i++) {
			SysRoleModel roleModel = null;
			roleModel = this.sysRoleManager.get(this.get(userRoleIdsArray[i]).getRoleId());
			SysUserModel userModel = null;
			userModel = this.sysUserManager.get(this.get(userRoleIdsArray[i]).getUserId());

			this.sysLogManager.saveSysLog("user", "解除用户角色", 1, "解除[" + userModel.getName() + "]用户的[" + roleModel.getName() + "]角色", "收回权限成功", null);
			this.removeByPk(userRoleIdsArray[i]);
		}
		// sysLogManager.saveSysLog("user", "用户角色解除", 1, "解除成功", "解除成功", null);
	}
}
