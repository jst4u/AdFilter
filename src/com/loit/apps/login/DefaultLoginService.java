package com.loit.apps.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.loit.apps.system.entity.MenuListEntity;
import com.loit.apps.system.model.SysUserModel;
//import com.loit.apps.system.query.UserMenuQueryItem;
import com.loit.apps.system.service.SysUserManagerImpl;
import com.loit.apps.system.service.SysUserRoleManagerImpl;
import com.loit.core.security.AcegiUserDetails;
import com.loit.core.security.AcegiUserDetailsService;
import com.loit.core.spring.CommonManager;

@Service
public class DefaultLoginService extends CommonManager implements AcegiUserDetailsService {

	@Autowired
	private SysUserManagerImpl sysUserManager;
	
	@Autowired
	private SysUserRoleManagerImpl sysUserRoleManager;
	
	/**
	 * 根据用户名取用户信息，登录时，由Spring Security调用。
	 * 返回的对象会存放在session里，可以用下面的方法取得：
	 * SessionContextUserEntity.currentUser();
	 * @author cj
	 */
	public AcegiUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		SysUserModel userExample = new SysUserModel();
		userExample.setLoginName(username);
		List<SysUserModel> users = sysUserManager.findByExample(userExample, null, null);
		if (users.size() == 0) {
			throw new UsernameNotFoundException("User " + username + " not found or has been forbidden");
		}
		SysUserModel user = users.iterator().next();
		if(user.getState().equals("F")){
			throw new UsernameNotFoundException("User " + username + " not found or has been forbidden");
		}
		if(user.getState().equals("S")){
			throw new UsernameNotFoundException("User " + username + " not found or has been forbidden");
		}
		CurrentUser contextUser = new CurrentUser();
		contextUser.setUserId(user.getUserId());
		contextUser.setUsername(user.getLoginName());
		contextUser.setFullname(user.getName());
		contextUser.setPassword(user.getPassword());
		contextUser.setSysUserModel(user);
		
		
		
//		获取用户权限菜单
//		UserMenuQueryCondition c = new UserMenuQueryCondition();
//		c.setUserId(contextUser.getUserId());
//		UserMenuQueryItem example = new UserMenuQueryItem();
//		example.setUserId(contextUser.getUserId());
//		List<UserMenuQueryItem> userMenuQueryItem = this.dao.findByExample(example);
//		List<UserMenuQueryItem> userMenuQueryItem = this.dao.query(c, UserMenuQueryItem.class);
		
		if(user!=null){
			sysUserManager.saveUserLoginLog(user);
		}
		return contextUser;
	}

	
//	public Collection<SysMenuGroupEntity> getAllMenusForCurrentUser() {
//		
//		
//		
//		List<SysMenuGroupEntity> menuGroupList = this.dao.createCommonQuery(SysMenuGroupEntity.class)
//		.setOrderBy(SysMenuGroupModel.FieldNames.menuGrpSeq)
//		.query();
//		Map<String, SysMenuGroupEntity> menuGroupMap = new LinkedHashMap<String, SysMenuGroupEntity>();
//		for (SysMenuGroupEntity sysMenuGroupEntity : menuGroupList) {
//			menuGroupMap.put(sysMenuGroupEntity.getSysMenuGroupUuid(), sysMenuGroupEntity);
//		}
//		List<SysMenuItemModel> menuItemList = this.dao.createCommonQuery(SysMenuItemModel.class)
//		.setOrderBy(SysMenuItemModel.FieldNames.menuItemSeq)
//		.query();
//		for (SysMenuItemModel sysMenuItemModel : menuItemList) {
//			if (sysMenuItemModel.getSysMenuGroupUuid() == null) {
//				continue;
//			}
//			menuGroupMap.get(sysMenuItemModel.getSysMenuGroupUuid()).getMenuItemList().add(sysMenuItemModel);
//		}
//		return menuGroupMap.values();
//	}
	
	
//	/**
//	 * 递归菜单项
//	 * @param menuItemList 
//	 * @param id 菜单的下级菜单
//	 * @return
//	 */
//	private List<MenuListEntity>  recursionMenu(List<MenuListEntity> menuItemList, String id) {
//		List<MenuListEntity> list = new ArrayList<MenuListEntity>(); 
//		menuItemList.get(0);
//		for(MenuListEntity menuitem : menuItemList){
//			if(menuitem.getParentId().equals(id)){
//				list.add(menuitem);
//				menuitem.setMenuItemList(recursionMenu(menuItemList,menuitem.getFuncId()));
//			}
//		}
//			
//		return list;
//	}
	
	
//	public List<MenuListEntity> getUserMenusForCurrentUser(String menuId) {
//		List<MenuListEntity> result = new ArrayList<MenuListEntity>();
//		
//		//获取当前用户所有的菜单项目
////		UserMenuQueryCondition c = new UserMenuQueryCondition();
////		c.setUserId(CurrentUser.currentUser().getUserId());
////		
////		List<UserMenuQueryItem> allMenus =this.dao.query(c, UserMenuQueryItem.class);
//		UserMenuQueryItem example = new UserMenuQueryItem();
//		example.setUserId(CurrentUser.currentUser().getUserId());
//		List<UserMenuQueryItem> allMenus = this.dao.findByExample(example);
//		
//		//定义三级菜单
//		List<MenuListEntity> menu1 = new ArrayList<MenuListEntity>();
//		List<MenuListEntity> menu2 = new ArrayList<MenuListEntity>();
//		List<MenuListEntity> menu3 = new ArrayList<MenuListEntity>();
//		
//		for (UserMenuQueryItem userMenuQueryItem : allMenus) {
//			MenuListEntity e = new MenuListEntity();
//			e.setUserId(userMenuQueryItem.getUserId());
//			e.setFuncId(userMenuQueryItem.getFuncId());
//			e.setFuncCode(userMenuQueryItem.getFuncCode());
//			e.setName(userMenuQueryItem.getName());
//			e.setParentId(userMenuQueryItem.getParentId());
//			e.setFuncLevel(userMenuQueryItem.getFuncLevel());
//			e.setFunSeq(userMenuQueryItem.getFunSeq());
//			e.setViewname(userMenuQueryItem.getViewname());
//			e.setFuncImg( userMenuQueryItem.getFuncImg());
//			e.setFuncType(userMenuQueryItem.getFuncType());
//			e.setSys(userMenuQueryItem.getSys());
//			menu1.add(e);
//			menu2.add(e);
//			menu3.add(e);
//		}
//		
//		
//		menu1.get(0);
//		for (MenuListEntity menuListEntity1 : menu1) {
//			if(menuListEntity1.getParentId().equals("1")){
//				
//				menu2.get(0);
//				for (MenuListEntity menuListEntity2 : menu2) {
//					if(menuListEntity2.getParentId().equals(menuListEntity1.getFuncId())){
//
//						menu3.get(0);
//						for (MenuListEntity menuListEntity3 : menu3) {
//							if(menuListEntity3.getParentId().equals(menuListEntity2.getFuncId())){
//								menuListEntity2.getMenuItemList().add(menuListEntity3);
//							}
//						}
//						menuListEntity1.getMenuItemList().add(menuListEntity2);
//						
//					}
//				}
//				result.add(menuListEntity1);
//			}
//			
//		}
//		
//		System.out.println(result);
//		return result;
//	}
}
