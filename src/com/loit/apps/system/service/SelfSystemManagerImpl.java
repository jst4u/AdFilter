package com.loit.apps.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.login.CurrentUser;
import com.loit.apps.system.model.SysFunctionModel;
import com.loit.core.exception.SysException;
import com.loit.core.spring.CommonManager;

@Service
public class SelfSystemManagerImpl extends CommonManager{
	
	@Autowired
	private SysFunctionManagerImpl sysFunctionManager;
		
	public List<SysFunctionModel> getAllFunctionByUserId(String userId){
		SysFunctionModel sfm = new SysFunctionModel();
		sfm.setParentId("1");
		return this.dao.findByExample(sfm);
//		return null;
	} 
	
	public List<SysFunctionModel> getAllCurrentUserFunction(){
		List<SysFunctionModel> res = new ArrayList<SysFunctionModel>();
		CurrentUser cu = CurrentUser.currentUser();
		if(cu == null){
			throw new SysException("超时，请重新登录！");
		}
		String userId = cu.getUserId();
		String sql = "(select c.FUNC_ID, c.FUN_SEQ from sys_user_role a, sys_role_func b, sys_function c where a.ROLE_ID = b.ROLE_ID and b.FUNC_ID = c.FUNC_ID "
				+ " and a.USER_ID = '" + userId + "') union (select b.FUNC_ID, b.FUN_SEQ from sys_user_func a, sys_function b "
				+ " where a.func_id = b.FUNC_ID and a.USER_ID = '" + userId + "') order by fun_seq";
		List<HashMap<String, String>> list = this.dao.findBySql(sql, null, null, null);
		if(null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String fid = list.get(i).get("FUNC_ID");
				SysFunctionModel funcModel = sysFunctionManager.get(fid);
				res.add(funcModel);
			}
		}
		return res;
	} 
	
	/**
	 * 获取当前用户顶层菜单
	 * @return
	 */
	public List<SysFunctionModel> getTopFunctionForCurrentUser(){
		List<SysFunctionModel> res = new ArrayList<SysFunctionModel>();
		CurrentUser cu = CurrentUser.currentUser();
		if(cu == null){
			throw new SysException("超时，请重新登录！");
		}
		String userId = cu.getUserId();
		String sql = "(select c.FUNC_ID, c.FUN_SEQ from sys_user_role a, sys_role_func b, sys_function c where a.ROLE_ID = b.ROLE_ID and b.FUNC_ID = c.FUNC_ID "
				+ " and c.PARENT_ID = '1' and a.USER_ID = '" + userId + "') union (select b.FUNC_ID, b.FUN_SEQ from sys_user_func a, sys_function b "
				+ " where a.func_id = b.FUNC_ID and b.PARENT_ID = '1' and a.USER_ID = '" + userId + "') order by fun_seq";
		List<HashMap<String, String>> list = this.dao.findBySql(sql, null, null, null);
		if(null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String fid = list.get(i).get("FUNC_ID");
				SysFunctionModel funcModel = sysFunctionManager.get(fid);
				res.add(funcModel);
			}
		}
		return res;
	} 
	
	public List<SysFunctionModel> getFunctionForCurrentUserByParent(String parentId){
		SysFunctionModel  sfm = new SysFunctionModel();
		sfm.setParentId("1");
		return this.dao.findByExample(sfm);
//		return null;
	} 

	public List<SysFunctionModel> getTopFunctionByUserId(String userId){
		return null;
	} 
}
