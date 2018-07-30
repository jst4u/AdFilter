package com.loit.core.utils.converter;

import org.apache.commons.beanutils.Converter;

public final class StringConverter
    implements Converter
{

    public StringConverter()
    {
    }

    public Object convert(Class type, Object value)
    {
        if(value == null)
            return null;
        else
            return value.toString();
    }
}
