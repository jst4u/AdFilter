package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.system.model.SysRoleFuncModel;
import com.loit.apps.system.model.SysRoleModel;
import com.loit.apps.system.model.SysUserRoleModel;
import com.loit.core.commom.SysConfig;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.core.utils.StringUtil;

@Service
public class SysRoleManagerImpl extends CommonManager {

	@Autowired
	private SysLogManagerImpl sysLogManager;


	public SysRoleModel get(String id) {
		return this.dao.get(SysRoleModel.class, id);
	}

	public List<SysRoleModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysRoleModel.class, orderBy, pagingInfo);
	}

	public List<SysRoleModel> findByExample(SysRoleModel example, String orderBy, PagingInfo pagingInfo) {
		return this.dao.findByExample(example, orderBy, pagingInfo);
	}

	public SysRoleModel save(SysRoleModel model) {
		String roleId = model.getRoleId();
		model = this.dao.save(model);
		return model;
	}

	public List<SysRoleModel> saveAll(Collection<SysRoleModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysRoleModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysRoleModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysRoleModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysRoleModel.class, ids);
	}

	/**
	 * 删除未赋权的角色
	 * 
	 * @param id
	 * @author Administrator
	 */
	public String delByPk(String id) {
		boolean res = false;
		// 判断该角色没有赋给任何用户
		SysUserRoleModel role = new SysUserRoleModel();
		role.setRoleId(id);
		List<SysUserRoleModel> rolemodel = this.dao.findByExample(role, null, null);
		if (rolemodel != null) {
			if (rolemodel.size() > 0) {
				res = false;
				return "2";
			} else {
				res = true;
			}
		} else {
			res = true;
		}

		// 判断该角色没有赋予任何权限
		SysRoleFuncModel func = new SysRoleFuncModel();
		func.setRoleId(id);
		List<SysRoleFuncModel> funcmodel = this.dao.findByExample(func, null, null);
		if (funcmodel != null) {
			if (funcmodel.size() > 0) {
				res = false;
				return "3";
			} else {
				res = true;
			}
		} else {
			res = true;
		}

		if (res) {
			this.removeByPk(id);
			sysLogManager.saveSysLog("role", "删除角色", 1, "删除成功", " 删除角色成功", null);
		}
		return "1";
	}

	/**
	 * 角色信息的保存
	 * 
	 * @param model
	 * @author Administrator
	 */
	public String saveModel(SysRoleModel model) {
		boolean res = false;
		String roleId = model.getRoleId();
		String name = model.getName();
		String code = model.getCode();
		if (roleId == null || "".equals(roleId)) {
			SysRoleModel examplename = new SysRoleModel();
			examplename.setName(name);
			List<SysRoleModel> namemodel = this.dao.findByExample(examplename, null, null);
			if (namemodel != null) {
				if (namemodel.size() > 0) {
					res = false;
					return "角色名称已存在，不能完成保存！";
				} else {
					res = true;
				}
			} else {
				res = true;
			}

			SysRoleModel examplecode = new SysRoleModel();
			examplecode.setCode(code);
			List<SysRoleModel> codemodel = this.dao.findByExample(examplecode, null, null);
			if (codemodel != null) {
				if (codemodel.size() > 0) {
					res = false;
					return "角色代码已存在，不能完成保存！";
				} else {
					res = true;
				}
			} else {
				res = true;
			}
			sysLogManager.saveSysLog("role", "添加角色", 1, "添加成功", " 添加" + model.getName() + "角色成功", null);
		} else {
			SysRoleModel examplename = new SysRoleModel();
			examplename.setName(name);
			List<SysRoleModel> namemodel = this.dao.findByExample(examplename, null, null);
			if (namemodel != null) {
				if (namemodel.size() > 1) {
					res = false;
					return "角色名称已存在，不能完成保存！";
				} else if (namemodel.size() == 1) {
					// 判断是否与其他角色名称一致
					String roleidtemp = ((SysRoleModel) namemodel.get(0)).getRoleId();
					if (roleidtemp.equals(roleId)) {
						res = true;
					} else {
						res = false;
						return "角色名称已存在，不能完成保存！";
					}
				} else {
					res = true;
				}
			} else {
				res = true;
			}

			SysRoleModel examplecode = new SysRoleModel();
			examplecode.setCode(code);
			List<SysRoleModel> codemodel = this.dao.findByExample(examplecode, null, null);
			if (codemodel != null) {
				if (codemodel.size() > 1) {
					res = false;
					return "角色代码已存在，不能完成保存！";
				} else if (codemodel.size() == 1) {
					// 判断是否与其他角色的代码一致
					String roleidtemp = ((SysRoleModel) codemodel.get(0)).getRoleId();
					if (roleidtemp.equals(roleId)) {
						res = true;
					} else {
						res = false;
						return "角色代码已存在，不能完成保存！";
					}
				} else {
					res = true;
				}
			} else {
				res = true;
			}
			sysLogManager.saveSysLog("role", "编辑角色", 1, "编辑成功", " 编辑" + model.getName() + "角色成功", null);
		}
		// 保存用户
		if (res) {
			this.save(model);
			// this.dao.save(model);
		}
		return "保存成功！";
	}

	// /**
	// * 根据用户Id查询用户所属角色
	// * @param userId
	// * @return
	// */
	// public List<UserRoleQueryItem> getRolesByUserId(String userId){
	// if(userId == null || "".equals(userId)){
	// throw new AppException("用户Id为空");
	// }
	// UserRoleQueryCondition condition = new UserRoleQueryCondition();
	// condition.setUserId(userId);
	// return new ArrayList<UserRoleQueryItem>();
	// // TODO
	// // return this.dao.query(condition, UserRoleQueryItem.class);
	// }
}
