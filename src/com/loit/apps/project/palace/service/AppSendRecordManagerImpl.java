package com.loit.apps.project.palace.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.project.palace.model.AppSendRecordModel;

@Service
public class AppSendRecordManagerImpl extends CommonManager{

	public AppSendRecordModel get(String id) {
		return this.dao.get(AppSendRecordModel.class, id);
	}

	public List<AppSendRecordModel> getAll() {
		return this.dao.getAll(AppSendRecordModel.class,null,null);
	}
	
	public List<AppSendRecordModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppSendRecordModel.class, orderBy, pagingInfo);
	}

	public List<AppSendRecordModel> findByExample(AppSendRecordModel example) {
		return this.dao.findByExample(example);
	}

	public AppSendRecordModel save(AppSendRecordModel model) {
		return this.dao.save(model);
	}

	public List<AppSendRecordModel> saveAll(Collection<AppSendRecordModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppSendRecordModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppSendRecordModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppSendRecordModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppSendRecordModel.class, ids);
	}

}
