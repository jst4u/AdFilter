package ${packageName}.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;
import ${packageName}.model.${className}Model;
<#--import ${packageName}.service.${className}Manager;-->

@Service
public class ${className}ManagerImpl extends CommonManager{

	public ${className}Model get(${pkFieldType} id) {
		return this.dao.get(${className}Model.class, id);
	}

	public List<${className}Model> getAll() {
		return this.dao.getAll(${className}Model.class,null,null);
	}
	
	public List<${className}Model> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(${className}Model.class, orderBy, pagingInfo);
	}

	public List<${className}Model> findByExample(${className}Model example) {
		return this.dao.findByExample(example);
	}

	public ${className}Model save(${className}Model model) {
		return this.dao.save(model);
	}

	public List<${className}Model> saveAll(Collection<${className}Model> models) {
		return this.dao.saveAll(models);
	}

	public void remove(${className}Model model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<${className}Model> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(${pkFieldType} id) {
		this.dao.removeByPk(${className}Model.class, id);
	}

	public void removeAllByPk(Collection<${pkFieldType}> ids) {
		this.dao.removeAllByPk(${className}Model.class, ids);
	}

}
