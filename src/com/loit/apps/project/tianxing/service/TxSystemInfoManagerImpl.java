package com.loit.apps.project.tianxing.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.project.tianxing.model.TxSystemInfoModel;

@Service
public class TxSystemInfoManagerImpl extends CommonManager{

	public TxSystemInfoModel get(Integer id) {
		return this.dao.get(TxSystemInfoModel.class, id);
	}

	public List<TxSystemInfoModel> getAll() {
		return this.dao.getAll(TxSystemInfoModel.class,null,null);
	}
	
	public List<TxSystemInfoModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(TxSystemInfoModel.class, orderBy, pagingInfo);
	}

	public List<TxSystemInfoModel> findByExample(TxSystemInfoModel example) {
		return this.dao.findByExample(example);
	}

	public TxSystemInfoModel save(TxSystemInfoModel model) {
		return this.dao.save(model);
	}

	public List<TxSystemInfoModel> saveAll(Collection<TxSystemInfoModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(TxSystemInfoModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<TxSystemInfoModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(Integer id) {
		this.dao.removeByPk(TxSystemInfoModel.class, id);
	}

	public void removeAllByPk(Collection<Integer> ids) {
		this.dao.removeAllByPk(TxSystemInfoModel.class, ids);
	}

}
