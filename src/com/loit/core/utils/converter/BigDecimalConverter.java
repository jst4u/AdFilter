package com.loit.core.utils.converter;

import java.math.BigDecimal;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public final class BigDecimalConverter
    implements Converter
{

    private Object defaultValue;
    private boolean useDefault;

    public BigDecimalConverter()
    {
        defaultValue = null;
        useDefault = false;
        defaultValue = null;
    }

    public BigDecimalConverter(Object defaultValue)
    {
        this.defaultValue = null;
        useDefault = true;
        this.defaultValue = defaultValue;
    }

    public Object convert(Class type, Object value)
    {
        if(value == null || "".equals(value.toString().trim()))
            return defaultValue;
        if(value instanceof BigDecimal)
            return value;
        try
        {
            return new BigDecimal(value.toString().trim());
        }
        catch(Exception e)
        {
            if(useDefault)
                return defaultValue;
            else
                throw new ConversionException(e);
        }
    }
}
