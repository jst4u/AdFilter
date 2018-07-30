package com.loit.apps.system.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.system.model.SysOrganizeModel;
import com.loit.apps.system.model.SysUserModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.exception.AppException;
import com.loit.core.spring.CommonManager;

@Service
public class SysOrganizeManagerImpl extends CommonManager{
	
	@Autowired
	private SysUserManagerImpl sysUserManager;
	
	public SysOrganizeModel get(String id) {
		return this.dao.get(SysOrganizeModel.class, id);
	}

	public List<SysOrganizeModel> getAll() {
		return this.dao.getAll(SysOrganizeModel.class,null,null);
	}
	
	public List<SysOrganizeModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysOrganizeModel.class, orderBy, pagingInfo);
	}

	public List<SysOrganizeModel> findByExample(SysOrganizeModel example) {
		return this.dao.findByExample(example);
	}

	public SysOrganizeModel save(SysOrganizeModel model) {
		SysOrganizeModel saveModel = this.dao.save(model);
		SysUserModel userModel = new SysUserModel();
		userModel.setUserId(saveModel.getOrganizeId());
		userModel.setName(saveModel.getName());
		userModel.setOrganizeId(saveModel.getParentOrganizeId());
		sysUserManager.save(userModel);
		return saveModel;
	}

	public List<SysOrganizeModel> saveAll(Collection<SysOrganizeModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysOrganizeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysOrganizeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		SysUserModel userModel = new SysUserModel();
		userModel.setOrganizeId(id);
		List<SysUserModel> userList = sysUserManager.findByExample(userModel, null, null);
		if(userList.isEmpty()){
			sysUserManager.delByPk(id);
			this.dao.removeByPk(SysOrganizeModel.class, id);
		}else{
			throw new AppException("该组织下有用户，请先移除组织下用户再删除组织");
		}
		
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysOrganizeModel.class, ids);
	}
	
	/**
	 * 获取所有下属组织ID（包含自身ID）
	 * @param orgId
	 * @return
	 */
	public List<String> getAllSubOrgIds (String orgId) {
		List<String> orgIds = new ArrayList<String>();
		if (null != orgId) {
			orgIds.add(orgId);
			SysOrganizeModel model = new SysOrganizeModel();
			model.setParentOrganizeId(orgId);
			List<SysOrganizeModel> list = this.findByExample(model);
			for (SysOrganizeModel som : list) {
				orgIds.addAll(getAllSubOrgIds(som.getOrganizeId()));
			}
		}
		return orgIds;
	}

}
