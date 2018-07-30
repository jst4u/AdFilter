package com.loit.core.web.tag.support;

import java.util.HashMap;

public class CustomMap extends HashMap
{
  public void put(String key, Object value)
  {
    value = value == null ? "" : value;
    super.put(key, value);
  }
}
