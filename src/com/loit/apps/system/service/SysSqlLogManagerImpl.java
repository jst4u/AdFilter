package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.system.model.SysSqlLogModel;

@Service
public class SysSqlLogManagerImpl extends CommonManager{

	public SysSqlLogModel get(String id) {
		return this.dao.get(SysSqlLogModel.class, id);
	}

	public List<SysSqlLogModel> getAll() {
		return this.dao.getAll(SysSqlLogModel.class,null,null);
	}
	
	public List<SysSqlLogModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysSqlLogModel.class, orderBy, pagingInfo);
	}

	public List<SysSqlLogModel> findByExample(SysSqlLogModel example) {
		return this.dao.findByExample(example);
	}

	public SysSqlLogModel save(SysSqlLogModel model) {
		return this.dao.save(model);
	}

	public List<SysSqlLogModel> saveAll(Collection<SysSqlLogModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysSqlLogModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysSqlLogModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysSqlLogModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysSqlLogModel.class, ids);
	}

}
