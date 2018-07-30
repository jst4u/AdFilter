package com.loit.core.utils.converter;

import java.sql.Date;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public final class DateConverter
    implements Converter
{

    private Object defaultValue;
    private boolean useDefault;

    public DateConverter()
    {
        defaultValue = null;
        defaultValue = null;
        useDefault = false;
    }

    public DateConverter(Object defaultValue)
    {
        this.defaultValue = null;
        this.defaultValue = defaultValue;
        useDefault = true;
    }

    public Object convert(Class type, Object value)
    {
        if(value == null || "".equals(value.toString().trim()))
            return defaultValue;
        if(value instanceof Date)
            return value;
        try
        {
            return Date.valueOf(value.toString());
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
