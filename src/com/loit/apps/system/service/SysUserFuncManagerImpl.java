package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.system.model.SysUserFuncModel;

@Service
public class SysUserFuncManagerImpl extends CommonManager{

	public SysUserFuncModel get(String id) {
		return this.dao.get(SysUserFuncModel.class, id);
	}

	public List<SysUserFuncModel> getAll() {
		return this.dao.getAll(SysUserFuncModel.class,null,null);
	}
	
	public List<SysUserFuncModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysUserFuncModel.class, orderBy, pagingInfo);
	}

	public List<SysUserFuncModel> findByExample(SysUserFuncModel example) {
		return this.dao.findByExample(example);
	}

	public SysUserFuncModel save(SysUserFuncModel model) {
		return this.dao.save(model);
	}

	public List<SysUserFuncModel> saveAll(Collection<SysUserFuncModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysUserFuncModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysUserFuncModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysUserFuncModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysUserFuncModel.class, ids);
	}

}
