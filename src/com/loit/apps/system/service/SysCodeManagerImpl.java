package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.system.model.SysCodeModel;

@Service
public class SysCodeManagerImpl extends CommonManager{

	public SysCodeModel get(String id) {
		return this.dao.get(SysCodeModel.class, id);
	}

	public List<SysCodeModel> getAll() {
		return this.dao.getAll(SysCodeModel.class,null,null);
	}
	
	public List<SysCodeModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysCodeModel.class, orderBy, pagingInfo);
	}

	public List<SysCodeModel> findByExample(SysCodeModel example) {
		return this.dao.findByExample(example);
	}

	public SysCodeModel save(SysCodeModel model) {
		return this.dao.save(model);
	}

	public List<SysCodeModel> saveAll(Collection<SysCodeModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysCodeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysCodeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysCodeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysCodeModel.class, ids);
	}

}
