package com.loit.core.spring;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.loit.core.commom.query.PagingInfo;
import com.loit.core.hibernate.model.BaseModel;
import com.loit.core.hibernate.model.OperationLog;
import com.loit.core.security.AcegiUserDetails;
import com.loit.core.security.SessionContext;
import com.loit.core.spring.support.CustomBeanWrapper;
import com.loit.core.utils.EntityUtil;
import com.loit.core.utils.ParameterUtil;
import com.loit.core.utils.SqlUtil;

@Repository
public class CommonDao extends HibernateDaoSupport {

	/**
	 * 
	 * 实现类设置hibernate数据源SessionFactory
	 * 
	 * @param sf
	 */
	@Autowired
	public final void setSessionFactoryAutowired(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void flush() {
		super.getHibernateTemplate().flush();
	}

	// 清除Hibernate内部缓存对象
	private void evictEntity(Object entity) {
		super.getHibernateTemplate().evict(entity);
	}

	// 判断Hibernate对象实体的ID类型，如果是Integer或Long，则转化成Long返回，否则不变
	private Serializable getEntityId(Class<? extends BaseModel> modelClass, Serializable id) {
		if ((id.getClass() == Integer.class) && (EntityUtil.getIdFieldType(modelClass) == Long.class)) {
			return Long.valueOf(((Integer) id).longValue());
		}
		return id;
	}

	/**
	 * 根据id获取数据对象
	 * 
	 * @param modelClass
	 * @param id
	 * @return
	 */
	public <MODEL extends BaseModel> MODEL get(Class<MODEL> modelClass, Serializable id) {
		Class<? extends BaseModel> entity = EntityUtil.getEntityClass(modelClass);
		id = getEntityId(entity, id);
		Object model = super.getHibernateTemplate().get(entity, id);
		if (model == null) {
			throw new ObjectRetrievalFailureException(modelClass, id);
		}
		evictEntity(model);
		return EntityUtil.convertEntityType(model, modelClass);
	}

	/**
	 * 查询所有modelClass数据对象
	 * 
	 * @param modelClass
	 * @param orderBy 可以为null
	 * @param pagingInfo 可以为null
	 * @return
	 */
	public <MODEL extends BaseModel> List<MODEL> getAll(Class<MODEL> modelClass, String orderBy, PagingInfo pagingInfo) {
		List<?> resultList = this.getAllFinal(EntityUtil.getEntityClass(modelClass), orderBy, pagingInfo);
		List<MODEL> resultFinal = new ArrayList<MODEL>();
		for (Iterator<?> localIterator = resultList.iterator(); localIterator.hasNext();) {
			Object entity = localIterator.next();
			this.evictEntity(entity);
			resultFinal.add(EntityUtil.convertEntityType(entity, modelClass));
		}
		return resultFinal;
	}

	/**
	 * 删除数据对象
	 * 
	 * @param model
	 */
	public <MODEL extends BaseModel> void remove(MODEL model) {
		super.getHibernateTemplate().delete(EntityUtil.getEntityClass(model.getClass()).getName(), model);
		flush();
	}

	/**
	 * 删除所有数据对象
	 * 
	 * @param models
	 */
	public <MODEL extends BaseModel> void removeAll(Collection<MODEL> models) {
		if (models.size() == 0) {
			return;
		}
		Iterator<MODEL> iter = models.iterator();
		while (iter.hasNext()) {
			MODEL model = iter.next();
			super.getHibernateTemplate().delete(EntityUtil.getEntityClass(model.getClass()).getName(), model);
		}
		flush();
	}

	/**
	 * 根据主键删除数据对象
	 * 
	 * @param modelClass
	 * @param id
	 */
	public <MODEL extends BaseModel> void removeByPk(Class<MODEL> modelClass, Serializable id) {
		remove(get(modelClass, id));
	}

	/**
	 * 根据主键集合删除所有数据对象
	 * 
	 * @param modelClass
	 * @param ids
	 */
	public <MODEL extends BaseModel> void removeAllByPk(Class<MODEL> modelClass, Collection<? extends Serializable> ids) {
		List<MODEL> b = new ArrayList<MODEL>();
		for (Iterator<? extends Serializable> localIterator = ids.iterator(); localIterator.hasNext();) {
			Serializable id = (Serializable) localIterator.next();
			b.add(this.get(modelClass, id));
		}
		removeAll(b);
	}

	/**
	 * 保存数据对象，RowState标记为“Deleted”除外
	 * 
	 * @param model
	 * @return
	 */
	public <MODEL extends BaseModel> MODEL save(MODEL model) {
		if ("Deleted".equals(model.getRowState())) {
			remove(model);
			return null;
		}
		// 后台校验需保存的数据
		if ((model.validFields().size() != 0) && (model.validFields().size() != EntityUtil.getFieldCount(model.getClass()))) {
			Serializable id = EntityUtil.getId(model);
			if (id != null) {
				Object obj = super.getHibernateTemplate().get(EntityUtil.getEntityClass(model.getClass()), id);
				if (obj != null) {
					this.evictEntity(obj);
					new CustomBeanWrapper(model).copyPropertiesTo(obj, model.validFields());
					if (model.getClass() == obj.getClass())
						model = (MODEL) obj;
					else {
						BeanUtils.copyProperties(obj, model);
					}
				}
			}
		}
		if ((model instanceof OperationLog)) {
			AcegiUserDetails user = SessionContext.getUser();
			String userId = user == null ? null : user.getUserId();
			// TODO 常规应该从数据库取时间，现在是从应用服务器取时间
			Date sysdate = new Date();
			OperationLog olog = (OperationLog) model;
			if (olog.getCreator() == null && (olog.getCreateTime() == null)) {
				olog.setCreator(userId);
				olog.setCreateTime(sysdate);
			} else {
				olog.setModifier(userId);
				olog.setModifyTime(sysdate);
			}
		}
		Object savedObj = super.getHibernateTemplate().merge(EntityUtil.getEntityClass(model.getClass()).getName(), model);
		flush();
		this.evictEntity(savedObj);
		if (model.getClass() == savedObj.getClass()) {
			return (MODEL) savedObj;
		}
		BeanUtils.copyProperties(savedObj, model);
		return model;
	}

	public <MODEL extends BaseModel> List<MODEL> findByExample(MODEL example) {
		return findByExample(example, null, null);
	}

	public <MODEL extends BaseModel> List<MODEL> findByExample(MODEL example, String orderBy) {
		return findByExample(example, orderBy, null);
	}

	public <MODEL extends BaseModel> List<MODEL> findByExample(MODEL example, String orderBy, PagingInfo pagingInfo) {
		return findByExample(example, null, null, orderBy, pagingInfo);
	}

	public <MODEL extends BaseModel> List<MODEL> findByExample(MODEL example, String sqlCondition, Object[] parameterValues, String orderBy, PagingInfo pagingInfo) {
		List resultList = this.findByExampleFinal(EntityUtil.getEntityClass(example.getClass()).getName(), example, sqlCondition, parameterValues, orderBy, pagingInfo);
		List resultFinal = new ArrayList();
		for (Iterator localIterator = resultList.iterator(); localIterator.hasNext();) {
			MODEL entity = (MODEL) localIterator.next();
			this.evictEntity(entity);
			resultFinal.add(EntityUtil.convertEntityType(entity, example.getClass()));
		}
		return resultFinal;
	}

	// TODO
	@Deprecated
	public List findByHql(final String hql, final String sqlCondition, final Object[] parameterValues, final String orderBy, final PagingInfo pagingInfo) {
		Assert.notNull(hql, "hql must not be null");
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException, HibernateException {
				List resultList = null;
				Query q = null;
				q = session.createQuery(hql);
				// queryPageInfo.setDataAllList(q.list());
				if (pagingInfo != null) {
					q.setFirstResult(pagingInfo.getCurrentRow());
					q.setMaxResults(pagingInfo.getPageSize());
					logger.info("queryBeginIndex:" + pagingInfo.getCurrentRow() + "  MaxResults:" + pagingInfo.getPageSize());
				}
				q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				resultList = q.list();
				return resultList;
			}
		});
		if (pagingInfo != null) {
			pagingInfo.setTotalRows(this.getCountRows(hql, "hql"));
		}
		return list;
	}

	// TODO 规定一条sql只能有一个order by，如果sql已经存在order by 则，去除order by 增加新的order by
	// 另未降低fix难度，规定 orderby的列必须在sql的select结果列中
	private String fixOrderBy(String sql, String orderby) {
		if (null == orderby) {
			return sql;
		}
		int indexOrderby = sql.indexOf(" order by ");
		if (indexOrderby < 0) {
			sql = sql + " order by " + orderby;
		} else {
			sql = sql.substring(0, indexOrderby) + " order by " + orderby;
		}
		return sql;
	}

	public List findBySql(final String sql, final String orderBy, final PagingInfo pagingInfo, final Class<?> resultBean) {
		Assert.notNull(sql, "sql must not be null");
		final String querySqlFinal = this.fixOrderBy(sql.toUpperCase(), null == orderBy ? null : orderBy.toUpperCase());
		List<?> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException, HibernateException {
				List<?> resultList = null;
				Query q = null;
				q = session.createSQLQuery(querySqlFinal);
				if (null != resultBean) {
					q.setResultTransformer(Transformers.aliasToBean(resultBean));
				} else {
					q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				}
				if (pagingInfo != null) {
					q.setFirstResult(pagingInfo.getCurrentRow());
					q.setMaxResults(pagingInfo.getPageSize());
					logger.info("queryBeginIndex:" + pagingInfo.getCurrentRow() + "  MaxResults:" + pagingInfo.getPageSize());
				}
				resultList = q.list();
				return resultList;
			}
		});
		if (pagingInfo != null) {
			pagingInfo.setTotalRows(this.getCountRows(querySqlFinal, "sql"));
		}
		return list;
	}

	private List findByExampleFinal(final String entityName, final BaseModel exampleEntity, final String sqlCondition, final Object[] parameterValues, final String orderBy, final PagingInfo pagingInfo) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		return super.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException, HibernateException {
				List resultList = null;
				Criteria criteriaQuery = null;
				if (null != entityName) {
					criteriaQuery = session.createCriteria(entityName);// a
				} else {
					criteriaQuery = session.createCriteria(exampleEntity.getClass());// a
				}
				criteriaExample(criteriaQuery, exampleEntity);
				findBySqlConditionDealCondition(session, criteriaQuery, sqlCondition, parameterValues);
				if (pagingInfo != null) {
					criteriaQuery.setProjection(Projections.rowCount());
					pagingInfo.setTotalRows(((Integer) criteriaQuery.uniqueResult()).intValue());
					criteriaQuery.setProjection(null);
					criteriaPaging(criteriaQuery, pagingInfo);
				}
				if (orderBy != null) {
					criteriaOrderBy(criteriaQuery, orderBy);
				}
				resultList = criteriaQuery.list();
				return resultList;
			}
		});
	}
	
	//TODO service排序
	private List orderPageList(final String entityName, final BaseModel exampleEntity,final String orderBy, final PagingInfo pagingInfo) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		return super.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException, HibernateException {
				List resultList = null;
				Criteria criteriaQuery = null;
				if (null != entityName) {
					criteriaQuery = session.createCriteria(entityName);// a
				} else {
					criteriaQuery = session.createCriteria(exampleEntity.getClass());// a
				}
				//........................
				if (pagingInfo != null) {
					criteriaQuery.setProjection(Projections.rowCount());
					pagingInfo.setTotalRows(((Integer) criteriaQuery.uniqueResult()).intValue());
					criteriaQuery.setProjection(null);
					criteriaPaging(criteriaQuery, pagingInfo);
				}
				if (orderBy != null) {
					criteriaOrderBy(criteriaQuery, orderBy);
				}
				resultList = criteriaQuery.list();
				return resultList;
			}
		});
	}

	/**
	 * 保存所有数据对象，RowState标记为“Deleted”除外 批量save请循环调用save()方法
	 * 
	 * @param models
	 * @return
	 */
	@Deprecated
	public <MODEL extends BaseModel> List<MODEL> saveAll(Collection<MODEL> models) {
		List<MODEL> saves = new ArrayList<MODEL>();
		List<MODEL> dels = new ArrayList<MODEL>();
		// models.iterator();
		Iterator<MODEL> iter = models.iterator();
		while (iter.hasNext()) {
			MODEL model = iter.next();
			// TODO do with Deleted rows
			if ("Deleted".equals(model.getRowState())) {
				dels.add(model);
				continue;
			} else {
				saves.add(model);
			}
			// 后台校验需保存的数据
			if ((model.validFields().size() != 0) && (model.validFields().size() != EntityUtil.getFieldCount(model.getClass()))) {
				Serializable id = EntityUtil.getId(model);
				if (id != null) {
					Object obj = super.getHibernateTemplate().get(EntityUtil.getEntityClass(model.getClass()), id);
					if (obj != null) {
						this.evictEntity(obj);
						new CustomBeanWrapper(model).copyPropertiesTo(obj, model.validFields());
						if (model.getClass() == obj.getClass())
							model = (MODEL) obj;
						else {
							BeanUtils.copyProperties(obj, model);
						}
					}
				}
			}
		}

		removeAll(dels);
		flush();
		if (saves.size() == 0) {
			return new ArrayList();
		}
		Iterator<MODEL> iterSaves = saves.iterator();
		while (iterSaves.hasNext()) {
			MODEL model = iterSaves.next();
			if (!(model instanceof OperationLog)) {
				continue;
			}
			AcegiUserDetails user = SessionContext.getUser();
			String userId = user == null ? null : user.getUserId();

			// TODO 常规应该从数据库取时间，现在是从应用服务器取时间
			Date sysdate = new Date();
			OperationLog olog = (OperationLog) model;
			if (olog.getCreator() == null && (olog.getCreateTime() == null)) {
				olog.setCreator(userId);
				olog.setCreateTime(sysdate);
			} else {
				olog.setModifier(userId);
				olog.setModifyTime(sysdate);
			}
		}

		// TODO mergeAll
		// super.getHibernateTemplate().mergeAll(
		// EntityUtil.getEntityClass(((BaseModel)n.iterator().next()).getClass()).getName(),
		// n);
		// List k = null;
		// flush();
		// Object i = new ArrayList();
		// for (int f = 0; f < n.size(); f++) {
		// BaseModel c = (BaseModel) n.get(f);
		// Object b = k.get(f);
		// this.evictEntity(b);
		// if (c.getClass() == b.getClass()) {
		// ((List) i).add((BaseModel) b);
		// } else {
		// BeanUtils.copyProperties(b, c);
		// ((List) i).add(c);
		// }
		// }
		// return (List<MODEL>) i;
		return null;
	}

	// 查询所有modelClass数据对象
	private List<?> getAllFinal(final Class<?> modelClass, final String orderBy, final PagingInfo pagingInfo) {
		return super.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException, HibernateException {
				List<?> resultList = null;
				Criteria criteriaQuery = session.createCriteria(modelClass);// a
				if (pagingInfo != null) {
					criteriaQuery.setProjection(Projections.rowCount());
					pagingInfo.setTotalRows(((Integer) criteriaQuery.uniqueResult()).intValue());
					criteriaQuery.setProjection(null);
					// query with orderby
					criteriaPaging(criteriaQuery, pagingInfo);
				}
				if ((orderBy != null) && (orderBy.trim().length() != 0)) {
					criteriaOrderBy(criteriaQuery, orderBy);
				}
				resultList = criteriaQuery.list();
				return resultList;
			}
		});
	}

	// 为查询类Criteria增加分页条件信息
	private void criteriaPaging(Criteria criteria, PagingInfo pagingInfo) {
		if (pagingInfo != null) {
			if (pagingInfo.getPageSize() <= 0) {
				pagingInfo.setPageSize(10);
			}
			if (pagingInfo.getCurrentPage() > pagingInfo.getTotalPages()) {
				pagingInfo.setCurrentPage(pagingInfo.getTotalPages());
			}
			if (pagingInfo.getCurrentPage() <= 0) {
				pagingInfo.setCurrentPage(1);
			}
			criteria.setFirstResult(pagingInfo.getCurrentRow());
			criteria.setMaxResults(pagingInfo.getPageSize());
		}
	}

	// 为查询类Criteria增加排序条件信息
	private void criteriaOrderBy(Criteria criteria, String orderBy) {
		if (orderBy != null && orderBy.trim().length() > 0) {
			Order[] orders = SqlUtil.parseOrderByToHibernateOrders(orderBy);
			for (int i = 0; i < orders.length; i++) {
				criteria.addOrder(orders[i]);
			}
		}
	}

	// 按照examlpe对象数据给查询对象增加条件
	private void criteriaExample(Criteria criteria, BaseModel exampleEntity) {
		CustomBeanWrapper cb = new CustomBeanWrapper(exampleEntity);
		for (String fieldName : EntityUtil.getFieldNames(exampleEntity.getClass())) {
			Object value = cb.getPropertyValue(fieldName);
			if (ParameterUtil.isParamValid(value)) {
				criteria.add(Restrictions.eq(fieldName, value));
			}
		}
	}

	private void findBySqlConditionDealCondition(Session session, Criteria criteria, String sqlCondition, Object[] parameterValues) {
		if ((sqlCondition == null) || (sqlCondition.trim().length() == 0)) {
			return;
		}
		if ((parameterValues == null) || (parameterValues.length == 0)) {
			criteria.add(Restrictions.sqlRestriction(sqlCondition));
		} else {
			boolean hasParam = false;
			for (int i = 0; i < parameterValues.length; i++) {
				Object param = parameterValues[i];
				if (param != null && param.getClass().isArray()) {
					hasParam = true;
					break;
				}
			}

			if (hasParam) {
				int[] params = SqlUtil.getOrdinalParameterLocations(sqlCondition);// h
				StringBuilder f = new StringBuilder(sqlCondition);
				Object e = new ArrayList<Object>();
				for (int i = parameterValues.length - 1; i >= 0; i--) {
					Object param = parameterValues[i];
					if (param != null) {
						if (param.getClass().isArray()) {
							int arrayLength = Array.getLength(param);
							if (arrayLength == 0) {
								param = null;
								((List<Object>) e).add(param);
							} else {
								if (arrayLength > 1) {
									f.insert(params[i], StringUtils.repeat("?, ", arrayLength - 1));
								}
								for (int j = arrayLength - 1; j >= 0; j--) {
									((List) e).add(Array.get(param, j));
								}
							}
						}

					}
				}
				parameterValues = ((List) e).toArray();
				sqlCondition = f.toString();
			}

			Type[] type = getParameterType(parameterValues);
			criteria.add(Restrictions.sqlRestriction(sqlCondition, parameterValues, type));
		}
	}

	private Type[] getParameterType(Object[] parameterValues) {
		Type[] type = new Type[parameterValues.length];
		for (int i = 0; i < parameterValues.length; i++) {
			Object param = parameterValues[i];
			if (param instanceof String) {
				type[i] = Hibernate.STRING;
			} else if (param instanceof Double) {
				type[i] = Hibernate.DOUBLE;
			} else if (param instanceof Integer) {
				type[i] = Hibernate.INTEGER;
			} else if (param instanceof Long) {
				type[i] = Hibernate.LONG;
			} else if (param instanceof Date) {
				type[i] = Hibernate.TIMESTAMP;
			} else {
				type[i] = Hibernate.STRING;
			}
		}
		return type;
	}

	public int getCountRows(final String querysql, final String queryType) {
		int countRows = 0;
		List list = null;
		list = (List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = " select count(*) as allsize ";
				int index = querysql.indexOf("from");
				if (index == -1)
					index = querysql.indexOf("FROM");
				int ix = querysql.indexOf("order");
				if (ix == -1)
					ix = querysql.indexOf("ORDER");
				if (index != -1) {
					if (ix == -1) {
						sql += querysql.substring(index).trim();
					} else {
						sql += querysql.substring(index, ix).trim();
					}
				}
				Query q = null;
				if ("sql".equalsIgnoreCase(queryType)) {
					q = session.createSQLQuery(sql);
				} else if ("hql".equalsIgnoreCase(queryType)) {
					q = session.createQuery(sql);
				}
				return q.list();
			}
		});

		if (list.size() > 0)
			countRows = Integer.parseInt(list.get(0).toString());
		return countRows;
	}

}