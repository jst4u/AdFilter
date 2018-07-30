package com.loit.core.dwr;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.directwebremoting.extend.Call;
import org.directwebremoting.extend.Reply;
import org.directwebremoting.impl.DefaultRemoter;

public class DWRRemoter extends DefaultRemoter {
	public Reply execute(Call call) {
		Reply reply;
		try {
			reply = super.execute(call);
			if (reply.getThrowable() != null) {
				Throwable exp;
				if ((exp = ExceptionUtils.getRootCause(reply.getThrowable())) == null) {
					exp = reply.getThrowable();
				}
				return new Reply(reply.getCallId(), reply.getReply(), exp);
			}
		} catch (Exception e) {
			return new Reply(null, null, e);
		}
		return reply;
	}
}
