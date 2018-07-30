package com.loit.core.hibernate;

import org.hibernate.dialect.SQLServerDialect;
/**
 * 用于Hibernate的SQLServer2005分页方言
 * @author heyong
 *
 */
public class SQLServer2005Dialect extends SQLServerDialect {
    static int getLastIndexOfOrderBy(String sql){
        return sql.toLowerCase().lastIndexOf("order by ");
    }
    
    public String getLimitString(String querySelect, int offset, int limit ){
        int lastIndexOfOrderBy = getLastIndexOfOrderBy(querySelect);
        //　没有 order by 或第一页的情况下
        if(lastIndexOfOrderBy<0 || querySelect.endsWith(")") || offset==0)
            return super.getLimitString(querySelect, 0, limit);
        else {
            //取出 order by 语句
            String orderby = querySelect.substring(lastIndexOfOrderBy, querySelect.length());

            //取出select语句的下标
            int indexOfSelect = querySelect.toLowerCase().indexOf("select");
            
            //取出 select 之后 到 order by 之前的语句内容
            String clumnToWhere = querySelect.substring(indexOfSelect+6, lastIndexOfOrderBy);
            StringBuffer sql = new StringBuffer(querySelect.length()+100);
            sql.append("select * from (select top ")
            	.append((limit))
                .append(" ROW_NUMBER() OVER(").append(orderby).append(") as _page_row_num_hb, ")
                .append(clumnToWhere).append(" ) temp ")
                .append(" where  _page_row_num_hb  >")
                .append(offset);
            return sql.toString();
        }
    }
 
    
    //使offset 参数生效
    public boolean supportsLimitOffset(){
        return true;
    }
}
