package com.loit.apps.project.tianxing.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import com.loit.apps.project.tianxing.model.TxSoftwareInfoModel;

@Service
public class TxSoftwareInfoManagerImpl extends CommonManager{

	public TxSoftwareInfoModel get(Integer id) {
		return this.dao.get(TxSoftwareInfoModel.class, id);
	}

	public List<TxSoftwareInfoModel> getAll() {
		return this.dao.getAll(TxSoftwareInfoModel.class,null,null);
	}
	
	public List<TxSoftwareInfoModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(TxSoftwareInfoModel.class, orderBy, pagingInfo);
	}

	public List<TxSoftwareInfoModel> findByExample(TxSoftwareInfoModel example) {
		return this.dao.findByExample(example);
	}

	public TxSoftwareInfoModel save(TxSoftwareInfoModel model) {
		return this.dao.save(model);
	}

	public List<TxSoftwareInfoModel> saveAll(Collection<TxSoftwareInfoModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(TxSoftwareInfoModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<TxSoftwareInfoModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(Integer id) {
		this.dao.removeByPk(TxSoftwareInfoModel.class, id);
	}

	public void removeAllByPk(Collection<Integer> ids) {
		this.dao.removeAllByPk(TxSoftwareInfoModel.class, ids);
	}

}
