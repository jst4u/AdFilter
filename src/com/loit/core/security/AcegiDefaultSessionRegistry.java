package com.loit.core.security;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.concurrent.SessionAlreadyUsedException;
import org.springframework.security.concurrent.SessionInformation;
import org.springframework.security.concurrent.SessionRegistry;
import org.springframework.security.concurrent.SessionRegistryImpl;
import org.springframework.security.ui.session.HttpSessionDestroyedEvent;

public class AcegiDefaultSessionRegistry
  implements SessionRegistry, ApplicationListener
{
  private SessionRegistryImpl A = new SessionRegistryImpl();

  @Autowired(required=false)
  private AcegiSessionRegistry a;

  public Object[] getAllPrincipals() { if (this.a == null) {
      return this.A.getAllPrincipals();
    }
    return this.a.getAllPrincipals();
  }

  public SessionInformation[] getAllSessions(Object principal, boolean includeExpiredSessions)
  {
    if (this.a == null) {
      return this.A.getAllSessions(principal, includeExpiredSessions);
    }
    return this.a.getAllSessions(principal, includeExpiredSessions);
  }

  public SessionInformation getSessionInformation(String sessionId)
  {
    if (this.a == null) {
      return this.A.getSessionInformation(sessionId);
    }
    return this.a.getSessionInformation(sessionId);
  }

  public void refreshLastRequest(String sessionId)
  {
    if (this.a == null)
      this.A.refreshLastRequest(sessionId);
    else
      this.a.refreshLastRequest(sessionId);
  }

  public void registerNewSession(String sessionId, Object principal)
    throws SessionAlreadyUsedException
  {
    if (this.a == null)
      this.A.registerNewSession(sessionId, principal);
    else
      this.a.registerNewSession(sessionId, principal);
  }

  public void removeSessionInformation(String sessionId)
  {
    if (this.a == null)
      this.A.removeSessionInformation(sessionId);
    else
      this.a.removeSessionInformation(sessionId);
  }

  public void onApplicationEvent(ApplicationEvent event)
  {
    if ((event instanceof HttpSessionDestroyedEvent)) {
      String a = ((HttpSession)event.getSource()).getId();
      removeSessionInformation(a);
    }
  }
}