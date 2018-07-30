package com.loit.apps.system.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.login.CurrentUser;
import com.loit.apps.system.model.SysLogModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.exception.AppException;
import com.loit.core.spring.CommonManager;

@Service
public class SysLogManagerImpl extends CommonManager{

	@Autowired
	SysUserManagerImpl sysUserManager;
	
	public SysLogModel get(String id) {
		return this.dao.get(SysLogModel.class, id);
	}

	public List<SysLogModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysLogModel.class, orderBy, pagingInfo);
	}

	public List<SysLogModel> findByExample(SysLogModel example, String orderBy, PagingInfo pagingInfo) {
		return this.dao.findByExample(example, orderBy, pagingInfo);
	}

	public SysLogModel save(SysLogModel model) {
		return this.dao.save(model);
	}

	public List<SysLogModel> saveAll(Collection<SysLogModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysLogModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysLogModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysLogModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysLogModel.class, ids);
	}
	
	/**
	 * 将用户对系统的操作保存到系统日志表中
	 */
	public void saveSysLog(String operOject,String operAction,Integer result,String logDesc,String remarks,String state){
		if(null==operAction||"".equals(operAction)||null==operOject||"".equals(operOject)){
			throw new AppException("操作对象及操作动作不能为空，系统操作日志写入失败");
		}
		String userId = CurrentUser.currentUser().getUserId();
//		WlUserModel userModel = new WlUserModel();
//		userModel.setUserId(userId);
//		userModel = this.userManager.findByExample(userModel, null, null).get(0);
		
		SysLogModel model=new SysLogModel();
		model.setLogDate(new Date());
		model.setOperUserId(userId);
		model.setOperAction(operAction);
		model.setOperOject(operOject);
		model.setResult(result);
		//model.setLogDesc(userModel.getName() + logDesc);
		model.setLogDesc(logDesc);
		model.setState(state);
		model.setRemarks(remarks);
		this.save(model);
	}

}
