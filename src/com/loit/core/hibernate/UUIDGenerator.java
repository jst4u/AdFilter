package com.loit.core.hibernate;

import java.io.Serializable;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.loit.apps.system.model.SysUserModel;

public class UUIDGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		if (object instanceof SysUserModel) {//同步组织用户。如果是同步的SysUserModel，并已设定userId，就不再自动生成
			String userId = ((SysUserModel) object).getUserId();
			if (null != userId && !"".equals(userId)) {
				return userId;
			}
		}
		return UUID.randomUUID().toString();
	}

}
