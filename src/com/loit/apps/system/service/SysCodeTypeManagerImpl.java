package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.system.model.SysCodeTypeModel;

@Service
public class SysCodeTypeManagerImpl extends CommonManager{

	public SysCodeTypeModel get(String id) {
		return this.dao.get(SysCodeTypeModel.class, id);
	}

	public List<SysCodeTypeModel> getAll() {
		return this.dao.getAll(SysCodeTypeModel.class,null,null);
	}
	
	public List<SysCodeTypeModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysCodeTypeModel.class, orderBy, pagingInfo);
	}

	public List<SysCodeTypeModel> findByExample(SysCodeTypeModel example) {
		return this.dao.findByExample(example);
	}

	public SysCodeTypeModel save(SysCodeTypeModel model) {
		return this.dao.save(model);
	}

	public List<SysCodeTypeModel> saveAll(Collection<SysCodeTypeModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysCodeTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysCodeTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysCodeTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysCodeTypeModel.class, ids);
	}

}
