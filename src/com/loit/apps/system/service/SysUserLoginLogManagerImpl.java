package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loit.apps.system.model.SysUserLoginLogModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;


@Service
public class SysUserLoginLogManagerImpl extends CommonManager{

	public SysUserLoginLogModel get(String id) {
		return this.dao.get(SysUserLoginLogModel.class, id);
	}

	public List<SysUserLoginLogModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysUserLoginLogModel.class, orderBy, pagingInfo);
	}

	public List<SysUserLoginLogModel> findByExample(SysUserLoginLogModel example, String orderBy, PagingInfo pagingInfo) {
		return this.dao.findByExample(example, orderBy, pagingInfo);
	}

	public SysUserLoginLogModel save(SysUserLoginLogModel model) {
		return this.dao.save(model);
	}

	public List<SysUserLoginLogModel> saveAll(Collection<SysUserLoginLogModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysUserLoginLogModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysUserLoginLogModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysUserLoginLogModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysUserLoginLogModel.class, ids);
	}
}
