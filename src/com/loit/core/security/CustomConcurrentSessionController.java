package com.loit.core.security;


import org.springframework.security.Authentication;
import org.springframework.security.concurrent.ConcurrentSessionControllerImpl;

public class CustomConcurrentSessionController extends ConcurrentSessionControllerImpl
{
  protected int getMaximumSessionsForThisUser(Authentication authentication)
  {
    int c = super.getMaximumSessionsForThisUser(authentication);
    if (authentication == null)
      return c;
    Object b;
    if (((b = authentication.getPrincipal()) instanceof MaximumSessionsControlled))
    {
      int a;
      return (a = ((MaximumSessionsControlled)b).getMaximumSessions()) == 0
         ? -1 : a;
    }
    return c;
  }
}
