package com.loit.core.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UsernameNotFoundException;

public abstract interface AcegiUserDetailsService
{
  public abstract AcegiUserDetails loadUserByUsername(String paramString)
    throws UsernameNotFoundException, DataAccessException;
}