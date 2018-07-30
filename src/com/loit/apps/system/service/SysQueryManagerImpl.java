package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.system.model.SysQueryModel;

@Service
public class SysQueryManagerImpl extends CommonManager{

	public SysQueryModel get(String id) {
		return this.dao.get(SysQueryModel.class, id);
	}
	
	public SysQueryModel getByQueryCode(String queryCode) {
		SysQueryModel example = new SysQueryModel();
		List<SysQueryModel> result = this.findByExample(example);
		if(result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
		
	}

	public List<SysQueryModel> getAll() {
		return this.dao.getAll(SysQueryModel.class,null,null);
	}
	
	public List<SysQueryModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysQueryModel.class, orderBy, pagingInfo);
	}

	public List<SysQueryModel> findByExample(SysQueryModel example) {
		return this.dao.findByExample(example);
	}

	public SysQueryModel save(SysQueryModel model) {
		return this.dao.save(model);
	}

	public List<SysQueryModel> saveAll(Collection<SysQueryModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysQueryModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysQueryModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysQueryModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysQueryModel.class, ids);
	}

}
