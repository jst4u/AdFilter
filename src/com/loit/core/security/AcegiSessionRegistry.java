package com.loit.core.security;


import org.springframework.security.concurrent.SessionAlreadyUsedException;

public abstract interface AcegiSessionRegistry
{
  public abstract Object[] getAllPrincipals();

  public abstract AcegiSessionInformation[] getAllSessions(Object paramObject, boolean paramBoolean);

  public abstract AcegiSessionInformation getSessionInformation(String paramString);

  public abstract void refreshLastRequest(String paramString);

  public abstract void registerNewSession(String paramString, Object paramObject)
    throws SessionAlreadyUsedException;

  public abstract void removeSessionInformation(String paramString);

  public abstract void expire(String paramString);
}