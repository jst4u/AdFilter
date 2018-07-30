package com.loit.apps.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.project.model.TlPjUserRelationModel;
import com.loit.apps.system.model.SysRoleFuncModel;
import com.loit.apps.system.model.SysUserModel;
import com.loit.apps.system.service.SysUserManagerImpl;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.exception.AppException;
import com.loit.core.spring.CommonManager;

@Service
public class TlPjUserRelationManagerImpl extends CommonManager{

	@Autowired
	private SysUserManagerImpl sysUserManager;
	
	public TlPjUserRelationModel get(String id) {
		return this.dao.get(TlPjUserRelationModel.class, id);
	}

	public List<TlPjUserRelationModel> getAll() {
		return this.dao.getAll(TlPjUserRelationModel.class,null,null);
	}
	
	public List<TlPjUserRelationModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(TlPjUserRelationModel.class, orderBy, pagingInfo);
	}

	public List<TlPjUserRelationModel> findByExample(TlPjUserRelationModel example) {
		return this.dao.findByExample(example);
	}

	public TlPjUserRelationModel save(TlPjUserRelationModel model) {
		if ("1".equals(model.getRelationship())) {
			TlPjUserRelationModel m = new TlPjUserRelationModel();
			m.setPjId(model.getPjId());
			m.setRelationship("1");
			List<TlPjUserRelationModel> relationList = this.findByExample(m);
			for (TlPjUserRelationModel relationModel : relationList) {
				relationModel.setRelationship("0");
				this.dao.save(relationModel);
			}
			return this.dao.save(model);
			//保存员工
		} else if("0".equals(model.getRelationship())) {
				String organizeId = model.getUserId();
				SysUserModel sysUser = new SysUserModel();
				sysUser.setOrganizeId(organizeId);
				List<SysUserModel> sysUserList = this.dao.findByExample(sysUser);
				//排除组织
				if (sysUserList.size() == 0) {
					TlPjUserRelationModel m = new TlPjUserRelationModel();
					m.setPjId(model.getPjId());
					m.setUserId(model.getUserId());
					m.setRelationship("1");
					List<TlPjUserRelationModel> mlist = this.findByExample(m);
					//排除项目经理
					if(!mlist.isEmpty()){
						SysUserModel user = sysUserManager.get(model.getUserId());
						throw new AppException("用户-" + user.getName() + "-是本项目项目经理，已帮您移除！");
					}else{
						return this.dao.save(model);
					}
			}
			return null;
		} else {
			return this.dao.save(model);
		}
	}
	
	public TlPjUserRelationModel saveRelation(TlPjUserRelationModel model,SysUserModel user) {
		
		return this.dao.save(model);
	}
	
	/**
	 * 获取叶子节点
	 * @param example
	 * @return
	 */
	public List<TlPjUserRelationModel> findNodesByExample(TlPjUserRelationModel example) {
		List<TlPjUserRelationModel> res = new ArrayList<TlPjUserRelationModel>();
		List<TlPjUserRelationModel> list = this.findByExample(example);
		for (TlPjUserRelationModel userRelation : list) {
			if ("0".equals(userRelation.getRelationship())) {
				res.add(userRelation);
			}
		}
		return res;
	}

	public void deleteByExample(TlPjUserRelationModel example) {
		removeAll(findByExample(example));
	}
	public List<TlPjUserRelationModel> saveAll(Collection<TlPjUserRelationModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(TlPjUserRelationModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<TlPjUserRelationModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(TlPjUserRelationModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(TlPjUserRelationModel.class, ids);
	}

}
