package com.loit.apps.project.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.project.model.TlPjInfoModel;
import com.loit.apps.project.model.TlPjUserRelationModel;

@Service
public class TlPjInfoManagerImpl extends CommonManager{

	@Autowired
	private TlPjUserRelationManagerImpl tlPjUserRelationManager;
	
	public TlPjInfoModel get(String id) {
		return this.dao.get(TlPjInfoModel.class, id);
	}

	public List<TlPjInfoModel> getAll() {
		return this.dao.getAll(TlPjInfoModel.class,null,null);
	}
	
	public List<TlPjInfoModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(TlPjInfoModel.class, orderBy, pagingInfo);
	}

	public List<TlPjInfoModel> findByExample(TlPjInfoModel example) {
		return this.dao.findByExample(example);
	}

	public TlPjInfoModel save(TlPjInfoModel model) {
		TlPjInfoModel pjInfoModel = this.dao.save(model);
		TlPjUserRelationModel userRelation = new TlPjUserRelationModel();
		userRelation.setPjId(pjInfoModel.getPjId());
		userRelation.setRelationship("1");
		userRelation.setUserId(pjInfoModel.getPjManager());
		tlPjUserRelationManager.save(userRelation);
		return pjInfoModel;
	}

	public List<TlPjInfoModel> saveAll(Collection<TlPjInfoModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(TlPjInfoModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<TlPjInfoModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(TlPjInfoModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(TlPjInfoModel.class, ids);
	}

}
