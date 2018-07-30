package com.loit.core.dwr;

import java.util.Calendar;
import java.util.Date;

import org.directwebremoting.ConversionException;
import org.directwebremoting.convert.DateConverter;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.extend.NonNestedOutboundVariable;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;
import org.directwebremoting.util.JavascriptUtil;
import org.directwebremoting.util.LocalUtil;

import com.loit.core.utils.DateUtil;

public class FormatStringDateConverter extends DateConverter {

	public Object convertInbound(Class<?> paramType, InboundVariable data) throws ConversionException {
		try {
			String b = data.getValue();
			b = LocalUtil.urlDecode(b);
			return DateUtil.parse(b);
		} catch (Exception e) {
			throw new MarshallException(paramType, e);
		}
	}

	public OutboundVariable convertOutbound(Object data, OutboundContext outctx) throws ConversionException {
		Date e = new Date();
		if ((data instanceof Date)) {
			e = (Date) data;
		} else {
			if ((data instanceof Calendar)) {
				e = ((Calendar) data).getTime();
			} else {
				throw new MarshallException(data.getClass());
			}
		}
		String a = JavascriptUtil.escapeJavaScript(DateUtil.format(e));
		return new NonNestedOutboundVariable("\"" + a + "\"");
	}

}