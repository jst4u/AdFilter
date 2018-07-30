package com.loit.core.spring;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.loit.core.hibernate.model.BaseModel;

public class CommonManager {
	 protected final Log logger = LogFactory.getLog(getClass());
	  
	@Autowired
	  protected CommonDao dao;

	  protected void setRollbackOnly() { TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	  }
	  
	  public <MODEL extends BaseModel> List<MODEL> getAll(Class<MODEL> modelClass) {
			return this.dao.getAll(modelClass, null,null);
	  }
	  
//		/**
//		 * 此功能主要用于执行本地数据库sql语句
//		 */
//		public int excuteSql(String sql){
//			return this.dao.excuteSql(sql);
//		}
//		
//		/**
//		 * 新增数据库实体对象，
//		 * @param model
//		 */
//		public <MODEL extends BaseModel> void save(MODEL model){
//			this.dao.save(model);
//		}
//
//		/**
//		 * 新增或者更新数据库实体对象
//		 * @param entity
//		 */
//		public <MODEL extends BaseModel> void saveOrUpdate(MODEL model){
//			this.dao.getHibernateTemplate().saveOrUpdate(model);
//		}
//
//		/**
//		 * 更新数据库实体对象
//		 * @param entity
//		 */
//		public <MODEL extends BaseModel>void update(MODEL model) {
//			
//			
//			this.dao.getHibernateTemplate().update(model);
//		}
//		/**
//		 * 删除数据库实体对象
//		 * @param entity
//		 */
//		public void removeEntity(Object entity) {
//			this.dao.getHibernateTemplate().delete(entity);
//			
//		}
//		
//		/**
//		 * 根据主键查询单实体对象
//		 * @param clazz
//		 * @param id
//		 * @return
//		 */
//		public Object queryByID(Class<?> clazz, Serializable id) {
//			
//			Object entity = this.dao.getHibernateTemplate().get(clazz, id);
//
//			return entity;
//		}
//
//		public QueryPageInfo pagingJdbc(final QueryPageInfo queryPageInfo,final String sql){
//			
//			List list = this.dao.getHibernateTemplate().executeFind(new HibernateCallback() {
//				public Object doInHibernate(Session session) throws SQLException,
//						HibernateException {
//					List resultList = null;
//					Query q = null ;
//
//					q = session.createSQLQuery(sql);
//
//					q.setFirstResult((queryPageInfo.getPageNumber() - 1) * queryPageInfo.getPageSize());
//					q.setMaxResults(queryPageInfo.getPageSize());
//					q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//					log.info("queryBeginIndex:"+(queryPageInfo.getPageNumber() - 1) * queryPageInfo.getPageSize()+"  MaxResults:"+queryPageInfo.getPageSize());
//					resultList = q.list();
//					
//					return resultList;
//				}
//			});
//			queryPageInfo.setDataList(list);
//			
//			queryPageInfo.setFullSize(this.getCountRows(sql,QUERY_TYPE_SQL));
//			return queryPageInfo;
//		}
//		
//		public QueryPageInfo pagingHql(final QueryPageInfo queryPageInfo,final String sql){
//			
//			List list = this.dao.getHibernateTemplate().executeFind(new HibernateCallback() {
//				public Object doInHibernate(Session session) throws SQLException,
//						HibernateException {
//					List resultList = null;
//					Query q = null ;
//					q = session.createQuery(sql);
////					queryPageInfo.setDataAllList(q.list());
//					q.setFirstResult((queryPageInfo.getPageNumber() - 1) * queryPageInfo.getPageSize());
//					q.setMaxResults(queryPageInfo.getPageSize());
//					q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//					log.info("queryBeginIndex:"+(queryPageInfo.getPageNumber() - 1) * queryPageInfo.getPageSize()+"  MaxResults:"+queryPageInfo.getPageSize());
//					resultList = q.list();
//					return resultList;
//				}
//			});
//			
//			queryPageInfo.setDataList(list);
//			queryPageInfo.setFullSize(this.getCountRows(sql,QUERY_TYPE_HQL));
//			return queryPageInfo;
//		}
//		
//		
//		public int getCountRows(final String querysql,final String queryType) {
//			int countRows = 0;
//			List list = null;
//			list= (List) this.dao.getHibernateTemplate().execute(
//					new HibernateCallback(){			
//						public Object doInHibernate(Session session) throws HibernateException, SQLException {
//							String sql = " select count(*) as allsize ";
//							int index = querysql.indexOf("from");
//							if (index == -1)
//								index = querysql.indexOf("FROM");
//							int ix = querysql.indexOf("order");
//							if (ix == -1)
//								ix = querysql.indexOf("ORDER");
//							if (index != -1) {
//								if (ix == -1) {
//									sql += querysql.substring(index).trim();
//								} else {
//									sql += querysql.substring(index, ix).trim();
//								}
//							}
//							Query q = null ;
//							if(QUERY_TYPE_SQL.equalsIgnoreCase(queryType)){
//								q = session.createSQLQuery(sql);
//							}else if(QUERY_TYPE_HQL.equalsIgnoreCase(queryType)){
//								q = session.createQuery(sql);
//							}
//							return q.list();
//						}	
//					}
//			); 
//			
//			if (list.size() > 0)
//				countRows = Integer.parseInt(list.get(0).toString());
//			return countRows;
//		}
//		
//		
//		
//		public HibernateDao getHibernateDao() {
//			return dao;
//		}
//
//		public void setHibernateDao(HibernateDao dao) {
//			this.dao = dao;
//		}
//
//		public JdbcDao getJdbcDao() {
//			return jdbcDao;
//		}
//
//		public void setJdbcDao(JdbcDao jdbcDao) {
//			this.jdbcDao = jdbcDao;
//		}
//		
//		/**
//		 * 查询hql的所有数据，并根据codeMap进行code-value转换。
//		 * codeMap中列key应当大写
//		 * @param hql
//		 * @param codeMap 代码转换对象(可以为null)，第一层map内容为colume:代码map，代码map为code:value
//		 * @return
//		 */
//		public List<?> QueryHqlForMap(final String hql,final Map<String,Map<String,String>> codeMap){
//			List list = this.dao.getHibernateTemplate().executeFind(new HibernateCallback() {
//				public Object doInHibernate(Session session) throws SQLException,
//						HibernateException {
//					List resultList = null;
//					Query q = null ;
//					q = session.createQuery(hql);
//					q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//					resultList = q.list();
//					return resultList;
//				}
//			});
//			if(null != codeMap){
//				return this.replaceCodeByValue(list, codeMap);
//			}
//			return list;
//
//		}
//		
//		/**
//		 * 遍历sourceList的数据，检查列数据是否在codeMap存在转换值，存在即转换.
//		 * 注意sourceList中数据map的key与codeMap中key的大小写问题
//		 * @param sourceList(List<Map<?, ?>>)
//		 * @param codeMap
//		 * @return List<Map<?, ?>>
//		 */
//		public List<?> replaceCodeByValue(List<?> sourceList,Map<String,Map<String,String>> codeMap){
//			
//			if(null ==sourceList || sourceList.size()==0 || null == codeMap || codeMap.size() == 0){
//				return sourceList;
//			}
//			Set<?> replaceKeys = codeMap.keySet();
//			
//			String[] replaceCols = new String[replaceKeys.size()];
//			
//			replaceKeys.toArray(replaceCols);
//			
//			Map row = null;
//			Object cellValue = null;
//			for(int i=0;i<sourceList.size();i++){
//				row = (Map)sourceList.get(i);
//				for(int j=0;j<replaceCols.length;j++){
//					cellValue = row.get(replaceCols[j]);
//					//System.out.println("cellValue:"+cellValue+";");
//					if(null == cellValue){
//						continue;
//					}
//					cellValue = codeMap.get(replaceCols[j]).get(cellValue.toString());
//					if(null != cellValue){
//						row.put(replaceCols[j], cellValue);
//					}
//				}
//			}
//			return sourceList;
//		}
//		
//		
//		/**
//		 * 查询sql的所有数据，并根据codeMap进行code-value转换。
//		 * codeMap中列key应当大写
//		 * @param sql
//		 * @param codeMap 代码转换对象(可以为null)，第一层map内容为colume:代码map，代码map为code:value
//		 * @return List<Map<?, ?>>
//		 */
//		public List<?> QuerySqlForMap(final String sql,final Map<String,Map<String,String>> codeMap){
//			List list = this.dao.getHibernateTemplate().executeFind(new HibernateCallback() {
//				public Object doInHibernate(Session session) throws SQLException,
//						HibernateException {
//					List resultList = null;
//					Query q = null ;
//
//					q = session.createSQLQuery(sql);
//
//					q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//					resultList = q.list();
//					
//					return resultList;
//				}
//			});
//			if(null != codeMap){
//				return this.replaceCodeByValue(list, codeMap);
//			}
//			return list;
//		}
//		
//		/**
//		 * hql查询,返回唯一的值
//		 * @param hql
//		 * @return
//		 
//		public Object queryUnique(String hql){
//			List list=this.getHibernateDao().getHibernateTemplate().find(hql);
//			if(list==null||list.size()==0)
//				return null;
//			return list.get(0);
//		}*/
//		
//		/**
//		 * 生成流水号
//		 * @param tableName
//		 * @param i
//		 * @param rowName
//		 * @return
//		 */
//		public String getFlowNumber(String tableName, int i,String rowName) {
//			Integer count=0;
//			synchronized (this) {
//				if(StringUtils.isNotEmpty(rowName)){
//					count= getJdbcDao().getJdbcTemplate().queryForInt("select count("+rowName+")  from "+tableName+" ");
//				}else{
//					count= getJdbcDao().getJdbcTemplate().queryForInt("select count(*)  from "+tableName+" ");
//				}
//
//				String strCount = String.valueOf(count+1);
//				if (strCount.length() < i) {
//					strCount = "0" + strCount;
//					return nextFlowNumber(strCount,i);
//				}else{
//					return strCount;
//				}
//			}
//
//		}
//		public String nextFlowNumber(String strCount,int i){
//			if (strCount.length() < i) {
//				strCount = "0" + strCount;
//				return nextFlowNumber(strCount,i);
//			} else {
//				return strCount;
//			}
//
//		}
		
}
