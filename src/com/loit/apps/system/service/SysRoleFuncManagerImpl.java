package com.loit.apps.system.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loit.apps.system.model.SysFunctionModel;
import com.loit.apps.system.model.SysRoleFuncModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;

@Service
public class SysRoleFuncManagerImpl extends CommonManager {

	public SysRoleFuncModel get(String id) {
		return this.dao.get(SysRoleFuncModel.class, id);
	}

	public List<SysRoleFuncModel> getAll() {
		return this.dao.getAll(SysRoleFuncModel.class, null, null);
	}

	public List<SysRoleFuncModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysRoleFuncModel.class, orderBy, pagingInfo);
	}

	public List<SysRoleFuncModel> findByExample(SysRoleFuncModel example) {
		return this.dao.findByExample(example);
	}
	
	/**
	 * 只获取叶子节点
	 * @param example
	 * @return
	 */
	public List<SysRoleFuncModel> findNodesByExample(SysRoleFuncModel example) {
		List<SysRoleFuncModel> res = new ArrayList<SysRoleFuncModel>();
		List<SysRoleFuncModel> list = this.findByExample(example);
		for (SysRoleFuncModel roleFunc : list) {
			String parentId = roleFunc.getFuncId();
			SysFunctionModel sysFunc = new SysFunctionModel();
			sysFunc.setParentId(parentId);
			List<SysFunctionModel> sysFuncList = this.dao.findByExample(sysFunc);
			if (sysFuncList.size() == 0) {
				res.add(roleFunc);
			}
		}
		return res;
	}

	public void deleteByExample(SysRoleFuncModel example) {
		removeAll(findByExample(example));
	}
	
	public SysRoleFuncModel save(SysRoleFuncModel model) {
		return this.dao.save(model);
	}

	public List<SysRoleFuncModel> saveAll(Collection<SysRoleFuncModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysRoleFuncModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysRoleFuncModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysRoleFuncModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysRoleFuncModel.class, ids);
	}

}
