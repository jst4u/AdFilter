package com.loit.apps.system.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.system.model.SysFunctionModel;
import com.loit.apps.system.model.SysRoleFuncModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.exception.AppException;
import com.loit.core.spring.CommonManager;


@Service
public class SysFunctionManagerImpl extends CommonManager{
	

	@Autowired
	private SysLogManagerImpl sysLogManager;
	
	public SysFunctionModel get(String id) {
		return this.dao.get(SysFunctionModel.class, id);
	}

	public List<SysFunctionModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(SysFunctionModel.class, orderBy, pagingInfo);
	}

	public List<SysFunctionModel> findByExample(SysFunctionModel example, String orderBy, PagingInfo pagingInfo) {
		return this.dao.findByExample(example, orderBy, pagingInfo);
	}

	public SysFunctionModel save(SysFunctionModel model) {
		return this.dao.save(model);
	}

	public List<SysFunctionModel> saveAll(Collection<SysFunctionModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysFunctionModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysFunctionModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysFunctionModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysFunctionModel.class, ids);
	}
	public String saveModel(SysFunctionModel model){
		boolean res = false;
		String funcId = model.getFuncId();
		String name = model.getName();
		String funcCode = model.getFuncCode();
		if(funcId==null || "".equals(funcId)){
			SysFunctionModel nameexample = new SysFunctionModel();
			nameexample.setName(name);
			List<SysFunctionModel> namemodel = this.dao.findByExample(nameexample, null, null);
			if(namemodel!=null){
				if(namemodel.size()>0){
					res = false;
					throw new AppException("功能模块名称"+name+"存在同名操作，不能保存！");
				}else{
					res = true;
				}
			}else{
				res = true;
			}
			
			SysFunctionModel codeexample = new SysFunctionModel();
			codeexample.setFuncCode(funcCode);
			List<SysFunctionModel> codemodel = this.dao.findByExample(codeexample, null, null);
			if(codemodel!=null){
				if(codemodel.size()>0){
					res = false;
					throw new AppException("功能代码编号"+funcCode+"存在同名操作，不能保存！");
				}else{
					res = true;
				}
			}else{
				res = true;
			}
		}else{
			SysFunctionModel nameexample = new SysFunctionModel();
			nameexample.setName(name);
			List<SysFunctionModel> namemodel = this.dao.findByExample(nameexample, null, null);
			if(namemodel!=null){
				if(namemodel.size()>1){
					res = false;
					throw new AppException("功能模块名称"+name+"存在同名操作，不能保存！");
				}else if(namemodel.size()==1){
					String funcid = ((SysFunctionModel)namemodel.get(0)).getFuncId();
					if(funcid.equals(funcId)){
						res = true;
					}else{
						res = false;
						throw new AppException("功能模块名称"+name+"存在同名操作，不能保存！");
					}
				}else{
					res = true;
				}
			}else{
				res = true;
			}
				
			SysFunctionModel codeexample = new SysFunctionModel();
			codeexample.setFuncCode(funcCode);
			List<SysFunctionModel> codemodel = this.dao.findByExample(codeexample, null, null);
			if(codemodel!=null){
				if(codemodel.size()>1){
					res = false;
					throw new AppException("功能代码编号"+funcCode+"存在同名操作，不能保存！");
				}else if(codemodel.size()==1){
					String funcid = ((SysFunctionModel)codemodel.get(0)).getFuncId();
					if(funcid.equals(funcId)){
						res = true;
					}else{
						res = false;
						throw new AppException("功能代码编号"+funcCode+"存在同名操作，不能保存！");
					}
				}else{
					res = true;
				}
			}else{
				res = true;
			}
		}

		if(res){
			this.dao.save(model);
			sysLogManager.saveSysLog("fun", "增加", 1,  "增加 [" + model.getName() + "]功能模块成功","增加成功", null);
		}
		return "保存成功！";
	}
	/**
	 * 系统功能模块删除
	 */
	public String delByPk(String id){
		boolean res = false;
		
		SysFunctionModel model = this.dao.get(SysFunctionModel.class, id);
		String funcId = model.getFuncId();
		//模块存在下属模块不能删除
		SysFunctionModel func = new SysFunctionModel();
		func.setParentId(funcId);
		List<SysFunctionModel> funcmodel = this.dao.findByExample(func, null, null);
		if(funcmodel!=null){
			if(funcmodel.size()>0){
				res = false;
				return "2";
				//throw new AppException("该功能模块存在下属模块，不能进行删除操作！");
			}else{
				res = true;
			}
		}else{
			res = true;
		}
		
		//模块已分配角色不能删除
		SysRoleFuncModel example = new SysRoleFuncModel();
		example.setFuncId(funcId);
		List<SysRoleFuncModel> codeModel = this.dao.findByExample(example, null, null);
		if(codeModel!=null){
			if(codeModel.size()>0){
				res = false;
				return "3";
				//throw new AppException("该功能模块已赋权给了角色，不能进行删除操作！");
			}else{
				res = true;
			}
		}else{
			res = true;
		}
		
		//删除该系统代码
		if(res){
			this.dao.removeByPk(SysFunctionModel.class, id);
			sysLogManager.saveSysLog("fun", "删除", 1, "对功能模块[" + model.getName() + "]进行删除", "删除成功",  null);
		}
		return "1";
//		return "删除成功！";
	}
	
	//TODO
	public String getFunctionNameByUri(String uri) {
		return null;
//		List<SysFunctionModel> functions = dao.createCommonQuery(SysFunctionModel.class)
//		.addCondition(Condition.eq("viewname", uri))
//		.query();
//		if (functions.size() == 0) {
//			return "";
//		}
//		SysFunctionModel function = functions.get(0);
//		StringBuilder sb = new StringBuilder(function.getName());
//		while (function.getParentId() != null) {
//			try {
//				function = dao.get(SysFunctionModel.class, function.getParentId());
//				sb.insert(0, " → ");
//				sb.insert(0, function.getName());
//			} catch (ObjectRetrievalFailureException orfex) {
//				break;
//			}
//		}
//		return sb.toString();
	}
	
	public List<SysFunctionModel> getAllFunctionByUserId(String userId){
		return null;
	} 

}
