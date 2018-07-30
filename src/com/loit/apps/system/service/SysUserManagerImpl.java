package com.loit.apps.system.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loit.apps.login.CurrentUser;
import com.loit.apps.system.model.SysRoleModel;
import com.loit.apps.system.model.SysUserLoginLogModel;
import com.loit.apps.system.model.SysUserModel;
import com.loit.apps.system.model.SysUserRoleModel;
import com.loit.core.commom.SysConfig;
import com.loit.core.commom.fields.QueryField;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.commom.query.QueryInfo;
import com.loit.core.exception.AppException;
import com.loit.core.spring.CommonManager;
import com.loit.core.utils.WebUtil;

@Service
public class SysUserManagerImpl extends CommonManager {

	@Autowired
	private SysLogManagerImpl sysLogManager;

	@Autowired
	private SysUserLoginLogManagerImpl sysUserLoginLogManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SaltSource saltSource;

	@Autowired
	private SysUserRoleManagerImpl sysUserRoleManager;

	@Autowired
	private SysRoleManagerImpl sysRoleManager;


	@Autowired
	private SysOrganizeManagerImpl sysOrganizeManager;

	public SysUserModel get(String id) {
		return this.dao.get(SysUserModel.class, id);
	}

	public List<SysUserModel> getAll() {
		return this.dao.getAll(SysUserModel.class, null, null);
	}

	public List<SysUserModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysUserModel.class, orderBy, pagingInfo);
	}

	public List<SysUserModel> findByExample(SysUserModel example, String orderBy, PagingInfo pagingInfo) {
		return this.dao.findByExample(example, orderBy, pagingInfo);
	}

	/**
	 * 获取某组织及下属组织的用户（包含其他筛选条件）
	 * 
	 * @param param
	 * @return
	 */
	public List<SysUserModel> getUsers(QueryInfo param) {
		List<SysUserModel> res = new ArrayList<SysUserModel>();
		String orgId = "1";
		String loginName = null;
		String name = null;
		String state = null;
		Map<String, QueryField> map = param.getQueryFieldsMap();
		if (null != map && map.size() > 0) {
			if (map.containsKey("organizeId")) {
				orgId = map.get("organizeId").getFieldStringValue().trim();
			}
			if (map.containsKey("loginName")) {
				loginName = map.get("loginName").getFieldStringValue().trim();
			}
			if (map.containsKey("name")) {
				name = map.get("name").getFieldStringValue().trim();
			}
			if (map.containsKey("state")) {
				state = map.get("state").getFieldStringValue().trim();
			}
		}
		List<String> orgIds = sysOrganizeManager.getAllSubOrgIds(orgId);
		for (String id : orgIds) {
			SysUserModel model = new SysUserModel();
			model.setOrganizeId(id);
			model.setLoginName(loginName);
			model.setName(name);
			model.setState(state);
			res.addAll(this.findByExample(model, param.getOrderBy(), null));
		}
		// 分页
		PagingInfo pageInfo = param.getPagingInfo();
		if (null != pageInfo) {
			pageInfo.setTotalRows(res.size());
			List<SysUserModel> userList = new ArrayList<SysUserModel>();
			int i = pageInfo.getCurrentRow();
			int lastIndex = i + pageInfo.getPageSize();
			for (; i < lastIndex && i < res.size(); i++) {
				userList.add(res.get(i));
			}
			return userList;
		}
		return res;
	}

	public SysUserModel save(SysUserModel model) {
		String userId = model.getUserId();
		String name = model.getName();
		if (model.getOrganizeId() == null || "".equals(model.getOrganizeId())) {
			model.setOrganizeId("1");
		}
		model = this.dao.save(model);
		return model;
	}

	public List<SysUserModel> saveAll(Collection<SysUserModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysUserModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysUserModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		SysUserModel admin = this.dao.get(SysUserModel.class, id);
		String loginname = admin.getLoginName();
		if ("Admin".equals(loginname)) {
			throw new AppException("该用户为admin，不能进行删除操作！");
		}
		this.delByPk(id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysUserModel.class, ids);
	}

	public void saveModel(SysUserModel model) {
		boolean res = false;
		model.setLoginName(model.getLoginName().trim());
		String userId = model.getUserId();
		String loginname = model.getLoginName();
		String name = model.getName();
		boolean addFlag = true;
		if (userId == null || "".equals(userId)) {
			addFlag = true;
			SysUserModel example = new SysUserModel();
			example.setLoginName(loginname);
			example.setState("U");
			List<SysUserModel> listmodel = this.dao.findByExample(example, null, null);
			if (listmodel != null) {
				if (listmodel.size() > 0) {
					res = false;
					throw new AppException("用户" + name + "登录名已存在，不能保存！");
				} else {
					example.setState("F");
					List<SysUserModel> listmodel2 = this.dao.findByExample(example, null, null);
					if (listmodel2 != null) {
						if (listmodel2.size() > 0) {
							res = false;
							throw new AppException("用户" + name + "登录名已存在，不能保存！");
						} else {
							res = true;
						}
					}
				}
			} else {
				res = true;
			}
		} else {
			addFlag = false;
			SysUserModel example = new SysUserModel();
			example.setLoginName(loginname);
			example.setState("U");
			List<SysUserModel> listmodel = this.dao.findByExample(example, null, null);
			if (listmodel != null) {
				if (listmodel.size() > 1) {
					res = false;
					throw new AppException("登录名已存在，不能保存！");
				} else {
					example.setState("F");
					List<SysUserModel> listmodel2 = this.dao.findByExample(example, null, null);
					if (listmodel2 != null) {
						if (listmodel2.size() > 0) {
							res = false;
							throw new AppException("登录名已存在，不能保存！");
						} else {
							res = true;
						}
					}
				}
				if (listmodel.size() == 1) {
					// 判断是否是admin用户，若是不能进行编辑
					String adminname = ((SysUserModel) listmodel.get(0)).getLoginName();
					if (!"Admin".equals(adminname)) {
						res = true;
					} else {
						res = false;
						throw new AppException("该用户为Admin，不能保存！");
					}
					// 判断是否是与其他用户的登录名一致
					String useridtemp = ((SysUserModel) listmodel.get(0)).getUserId();
					if (useridtemp.equals(userId)) {
						res = true;
					} else {
						res = false;
						throw new AppException("登录名已存在，不能保存！");
					}
				} else {
					res = true;
				}
			} else {
				res = true;
			}
		}
		if (res) {
			if (addFlag) {
				// 加密密码
				CurrentUser tmpUserDetails = new CurrentUser();
				tmpUserDetails.setUsername(model.getLoginName());
				model.setPassword(passwordEncoder.encodePassword(model.getPassword(), saltSource.getSalt(tmpUserDetails)));
				model.setState("U");
				// 保存用户
				this.save(model);
				sysLogManager.saveSysLog("user", "添加用户", 1, "添加用户[" + model.getLoginName() + "]", " 添加[" + model.getLoginName() + "]成功", null);
			} else {
				// 保存用户
				this.save(model);
				sysLogManager.saveSysLog("user", "编辑用户", 1, "对用户[" + model.getLoginName() + "]进行编辑", " 编辑用户" + model.getLoginName() + "成功", null);
			}
		}
	}

	public void delByPk(String id) {
		// // 判断该用户是否是admin，若是则不能删除
		SysUserModel admin = this.dao.get(SysUserModel.class, id);
		// String loginname = admin.getLoginName();
		// if ("Admin".equals(loginname)) {
		// throw new AppException("该用户为admin，不能进行删除操作！");
		// }

		// 逻辑删除用户,即把用户的状态置为停用
		// this.dao.removeByPk(SysUserModel.class, id);
		admin.setState("S");
		admin.setOrganizeId("");
		this.dao.save(admin);
		/*
		 * //删除此用户所授予的功能 WlUserFuncModel funcModel =new WlUserFuncModel();
		 * funcModel.setUserId(id); List<WlUserFuncModel>lists =
		 * this.wlUserFuncManager.findByExample(funcModel, null, null); if(lists
		 * != null && lists.size()>0){ for(Object model : lists){ String funcId
		 * = ((WlUserFuncModel)model).getFuncId();
		 * this.wlUserFuncManager.removeByPk(funcId); } } //删除此用户所授予的角色
		 * WlUserRoleModel roleModel = new WlUserRoleModel();
		 * roleModel.setUserId(id); List<>
		 */
		sysLogManager.saveSysLog("user", "删除", 1, "删除用户[" + admin.getLoginName() + "]", "删除[" + admin.getLoginName() + "]成功", null);
	}

	/**
	 * 将禁用的用户启用
	 * 
	 * @param id
	 * @author inn
	 */
	public void enableUser(String id) {
		if (id == null || "".equals(id)) {
			throw new AppException("请选择要启用的用户!");
		}

		SysUserModel user = this.dao.get(SysUserModel.class, id);
		String status = user.getState();
		// 判断用户状态 如果用户当前已经是使用状态U则无需启用
		if (status.equals("U")) {
			throw new AppException("此用户可用，无需启用！");
		}
		user.setState("U");
		this.dao.save(user);
		sysLogManager.saveSysLog("user", "启用", 1, "对禁用用户[" + user.getLoginName() + "]进行启用", "启用" + user.getLoginName() + "成功", null);
	}

	/**
	 * 禁用启用用户
	 * 
	 * @param id
	 * @author inn
	 */
	public void forbidUser(String id) {
		if (id == null || "".equals(id)) {
			throw new AppException("请选择要禁用的用户!");
		}
		// 判断该用户是否是admin，若是则不能操作
		SysUserModel user = this.dao.get(SysUserModel.class, id);
		String loginname = user.getLoginName();
		if ("Admin".equals(loginname)) {
			throw new AppException("该用户为admin，不能进行禁用操作！");
		}

		String status = user.getState();
		// 判断用户状态 如果用户当前已经是禁用状态 F则无需禁用
		if (status.equals("F")) {
			throw new AppException("此用户已经被禁用，无需再次禁用！");
		}
		user.setState("F");
		this.dao.save(user);
		sysLogManager.saveSysLog("user", "禁用", 1, "对用户[" + user.getLoginName() + "]进行禁用操作", "禁用用户[" + user.getLoginName() + "]成功", null);
	}

	/**
	 * 更换当前登陆用户密码
	 */
	public int changePassword(String oldPassword, String password) {
		// 加密旧密码
		oldPassword = passwordEncoder.encodePassword(oldPassword, saltSource.getSalt(CurrentUser.currentUser()));
		if (CurrentUser.currentUser().getPassword().equals(oldPassword)) {
			SysUserModel user = this.dao.get(SysUserModel.class, CurrentUser.currentUser().getSysUserModel().getUserId());
			// 加密新密码
			// 如果改为可以修改其他人密码，此处要改
			password = passwordEncoder.encodePassword(password, saltSource.getSalt(CurrentUser.currentUser()));
			user.setPassword(password);
			this.dao.save(user);
			CurrentUser.currentUser().setPassword(password);
			// 保存密码修改日志
			sysLogManager.saveSysLog("user", "密码修改", 1, "修改成功", null, null);
			return 1;
		} else {
			sysLogManager.saveSysLog("user", "密码修改", 0, "修改失败", "原密码填写错误", null);
			return 0;
			// throw new AppException("原密码错误!");
		}
	}

	public void passwordReset(String id) {
		if (null == id) {
			id = CurrentUser.currentUser().getSysUserModel().getUserId();
		}
		SysUserModel user = this.get(id);
		String password = "888888";
		// 加密新密码
		CurrentUser tmpUserDetails = new CurrentUser();
		tmpUserDetails.setUsername(user.getLoginName());
		password = passwordEncoder.encodePassword(password, saltSource.getSalt(tmpUserDetails));
		user.setPassword(password);
		this.dao.save(user);
		sysLogManager.saveSysLog("user", "密码重置", 1, "对用户[" + user.getLoginName() + "]的密码进行重置", "重置成功", null);
	}

	/**
	 * 重置所有用户密码
	 */
	public void passwordResetAllUsers() {
		for (SysUserModel user : getAll(null, null)) {
			passwordReset(user.getUserId());
		}
	}

	/**
	 * 生成用户登录日志并将userLogId保存在session中，在登录到退出过程中存在
	 * 
	 * @param model
	 */
	public void saveUserLoginLog(SysUserModel model) {
		Date now = new Date();
		SysUserLoginLogModel wlUserLoginLogmodel = new SysUserLoginLogModel();
		HttpServletRequest request = WebUtil.getHttpRequest();
		wlUserLoginLogmodel.setLoginTime(now);// 登录时间
		wlUserLoginLogmodel.setUserId(model.getUserId());// 用户
		HttpSession session = null;
		if (request != null) {
			wlUserLoginLogmodel.setUserIp(request.getRemoteAddr());// 用户ip
			wlUserLoginLogmodel.setHostName(request.getRemoteHost());// 用户主机名
			session = request.getSession();
		}

		wlUserLoginLogmodel.setTryTimes(1);// 尝试登录次数
		wlUserLoginLogmodel = sysUserLoginLogManager.save(wlUserLoginLogmodel);
		if (session != null) {
			session.setAttribute("userLogId", wlUserLoginLogmodel.getUserLoginLogId());
		}
	}

	/**
	 * 用户退出系统时，更改用户登录日志中的退出时间
	 * 
	 * @param userLogId
	 */
	public void userLogoutLog(String userLogId) {
		try {
			SysUserLoginLogModel wlUserLoginLogmodel = sysUserLoginLogManager.get(userLogId);
			wlUserLoginLogmodel.setLogoutTime(new Date());
			sysUserLoginLogManager.save(wlUserLoginLogmodel);
		} catch (ObjectRetrievalFailureException e) {
		}
	}

	/**
	 * 获取当前用户Id的公用方法
	 * 
	 * @return
	 * @author inn
	 */
	public String getCurrentUserId() {
		String userId = CurrentUser.currentUser().getSysUserModel().getUserId();
		if (userId == null || "".equals(userId)) {
			throw new AppException("当前用户不存在");
		}
		return userId;
	}

	/**
	 * 通过组织编号来获取在该组织下的所有用户
	 * 
	 * @param orgId
	 * @return
	 */
	public List<SysUserModel> getUsersByOrgId(String orgId) {
		SysUserModel example = new SysUserModel();
		example.setOrganizeId(orgId);
		example.setState("U");// 状态为“使用用户”
		return this.findByExample(example, null, null);
	}

	/**
	 * 通过用户姓名获取可用用户
	 * 
	 * @param userName
	 * @return
	 */
	// public List<UsersByNameQueryItem> getUsersByUserName(String userName){
	// UsersByNameQueryCondition userExample = new UsersByNameQueryCondition();
	// userExample.setName(userName);
	// List<UsersByNameQueryItem> users = this.dao.query(userExample,
	// UsersByNameQueryItem.class);
	// return users;
	// }

	/**
	 * 通过组织Id查询组织内的成员
	 * 
	 * @param organizeId
	 * @return
	 */
	// public List<UserOrganizeQueryItem> getUsersByOrganizeId(String
	// organizeId){
	// if(organizeId == null || "".equals(organizeId)){
	// throw new AppException("组织Id为空");
	// }
	// UserOrganizeQueryCondition queryCondition = new
	// UserOrganizeQueryCondition();
	// queryCondition.setOrganizeId(organizeId);
	// return this.dao.query(queryCondition, UserOrganizeQueryItem.class);
	// }

	/**
	 * 通过角色Id查询角色内的成员
	 * 
	 * @param roleId
	 * @return
	 */
	// public List<RoleGrantedUserQueryItem> getUsersByRoleId(String roleId){
	// if(roleId == null || "".equals(roleId)){
	// throw new AppException("角色Id为空");
	// }
	// RoleGrantedUserQueryCondition queryCondition = new
	// RoleGrantedUserQueryCondition();
	// queryCondition.setRoleId(roleId);
	// return this.dao.query(queryCondition, RoleGrantedUserQueryItem.class);
	// }

	// @Override
	// public List<SysFunctionModel> getGrantedFuncListByUserId(String userId) {
	//
	// return null;
	// }
	//
	// @Override
	// public WebTreeNode getGrantedFuncTreeByUserId(String userId) {
	// return null;
	// }

	/**
	 * 在聊天模块中，根据条件查询用户
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	// public List<ChatUsersQueryItem>
	// findUsersByCondition(ChatUsersQueryCondition condition){
	// return this.dao.query(condition, ChatUsersQueryItem.class);
	// // return this.dao.createCommonQuery(condition,
	// ChatUsersQueryItem.class).query();
	// // return this.dao.findBySqlCondition(SysUserModel.class, hql,
	// obj,"online_status desc");
	// }

	/**
	 * 用于Moblie 用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public String mobileLogin(String userName, String password) {
		if (userName == null || "".equals(userName.trim())) {
			return "请输入您的用户名";
		}
		if (password == null || "".equals(password.trim())) {
			return "请输入您的密码";
		}
		SysUserModel userExample = new SysUserModel();
		userExample.setLoginName(userName);
		List<SysUserModel> users = this.findByExample(userExample, null, null);
		if (users.size() == 0) {
			return "User " + userName + " not found";
		}
		SysUserModel user = users.iterator().next();
		if (user.getState().equals("F")) {
			return "User " + userName + " has been forbidden";
		}
		if (user.getState().equals("S")) {
			return "User " + userName + " has been forbidden";
		}
		// SessionContextUserEntity contextUser = new
		// SessionContextUserEntity();
		// contextUser.setUserId(user.getUserId());
		// contextUser.setUsername(user.getLoginName());
		// contextUser.setFullname(user.getName());
		// contextUser.setPassword(user.getPassword());
		// contextUser.setSysUserModel(user);
		if (user != null) {
			saveUserLoginLog(user);
		}
		return "success";
	}

	/**
	 * 根据当前用户获取是否有系统特定管理员权限 true:管理员权限
	 */
	public boolean getRoleByUserName() {
		boolean flag = false;
		String userId = CurrentUser.currentUser().getUserId();
		SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
		sysUserRoleModel.setUserId(userId);
		List<SysUserRoleModel> sysUserRoleModels = sysUserRoleManager.findByExample(sysUserRoleModel, null, null);

		if (sysUserRoleModels.size() == 0) {
			flag = false;
		} else {
			for (SysUserRoleModel userRoleModel : sysUserRoleModels) {

				SysRoleModel wlRoleModel = sysRoleManager.get(userRoleModel.getRoleId());
				String roleCode = wlRoleModel.getCode();
				if (roleCode.equals("awsadmin") || roleCode.equals("admin") || roleCode.equals("zhifaduizhang") || roleCode.equals("anjianjulingdao")) {
					flag = true;
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * 自定义查询
	 * 
	 * @param queryInfo
	 * @return
	 */
	public List getUserRoles(QueryInfo queryInfo) {
		List l = new ArrayList<>();
		Map m = new HashMap();
		m.put("roleId", "sssssssssss");
		m.put("code", "codessssss");
		m.put("name", "我是角色");
		l.add(m);
		return l;
	}

	/**
	 * 自定义查询，查询用户拥有的权限
	 * 
	 * @param queryInfo
	 * @return
	 */
	public List getUserFunctions(QueryInfo queryInfo) {
		List l = new ArrayList<>();
		// 1.用户角色权限
		// sys_role_func
		String hql = "select funcId,funcCode,name " + "from SysFunctionModel " + "where funcId in " + "   (select rf.funcId " + "   from SysRoleFuncModel rf, SysUserRoleModel ur" + "   where ur.roleId=rf.roleId and ur.userId='c300ba5c-01e8-3727-b305-5dcc9ccae1ee')";
		List roleFuncs = this.dao.findByHql(hql, null, null, queryInfo.getOrderBy(), queryInfo.getPagingInfo());
		// 2.用户特殊权限
		// sys_user_func
		hql = "select funcId,funcCode,name " + " from SysFunctionModel sf,SysUserFuncModel uf" + " where sf.funcId = uf.funcId and   uf.userId='c300ba5c-01e8-3727-b305-5dcc9ccae1ee')";
		List userFuncs = this.dao.findByHql(hql, null, null, queryInfo.getOrderBy(), queryInfo.getPagingInfo());
		roleFuncs.addAll(userFuncs);

		return roleFuncs;
	}

	// public static void main(String[] arg){
	//
	// String userid = "";
	// String password = "888888";
	// //加密新密码
	// CurrentUser tmpUserDetails = new CurrentUser();
	// tmpUserDetails.setUsername(user.getLoginName());
	// password = passwordEncoder.encodePassword(password,
	// saltSource.getSalt(tmpUserDetails));
	// user.setPassword(password);
	// this.dao.save(user);
	//
	// sysLogManager.saveSysLog("user", "密码重置", 1,
	// "对用户["+user.getLoginName()+"]的密码进行重置", "重置成功", null);
	// }

	public static void main(String[] args) {
		try {
			Resource resource = new ClassPathResource("/ui.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String syntoactiviti = props.getProperty("activiti.identify.syntoactiviti", "true");
			boolean issys = Boolean.valueOf(syntoactiviti);
			System.out.println(issys);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
