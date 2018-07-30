package com.loit.core.commom.query;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.loit.core.hibernate.model.BaseModel;

@MappedSuperclass
public abstract class BaseQueryItem extends BaseModel
{

	private static final long serialVersionUID = 1L;
private String A;
  private Integer a;

  @Column(name="UUID__")
  @Id
  public String getUuid()
  {
    return this.A;
  }

  public void setUuid(String uuid) {
    this.A = uuid;
  }
  @Transient
  public Integer getRownum() {
    return this.a;
  }

  public void setRownum(Integer rownum) {
    this.a = rownum;
  }
}
