package com.loit.apps.login;

import com.loit.apps.system.model.SysUserModel;
import com.loit.core.security.AcegiDefaultUserDetails;
import com.loit.core.security.SessionContext;

@SuppressWarnings("serial")
public class CurrentUser extends AcegiDefaultUserDetails {

	private SysUserModel userModel;
	private String[] userMenus = null;

	/**
	 * 取得当前登陆用户信息
	 * 
	 * @return
	 */
	public static CurrentUser currentUser() {
		return (CurrentUser) SessionContext.getUser();
	}

	public void setSysUserModel(SysUserModel userModel) {
		this.userModel = userModel;
	}

	public SysUserModel getSysUserModel() {
		return this.userModel;
	}

	public String[] getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(String[] userMenus) {
		this.userMenus = userMenus;
	}

	public String getUsername() {
		return super.getUsername();
	}
}
