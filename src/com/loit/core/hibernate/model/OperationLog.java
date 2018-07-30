package com.loit.core.hibernate.model;

import java.util.Date;

/**
 * 操作日志字段，原则上所有业务对象表均需要实现此接口，数据对象可根据需要决定是否实现
 * @author niujiale
 *
 */
public interface OperationLog {
	
	public abstract String getCreator();

	public abstract void setCreator(String paramString);

	public abstract Date getCreateTime();

	public abstract void setCreateTime(Date paramDate);

	public abstract String getModifier();

	public abstract void setModifier(String paramString);

	public abstract Date getModifyTime();

	public abstract void setModifyTime(Date paramDate);
	
}
