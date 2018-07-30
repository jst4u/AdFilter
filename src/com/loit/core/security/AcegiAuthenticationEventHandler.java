package com.loit.core.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;

public abstract interface AcegiAuthenticationEventHandler
{
  public abstract void onPreAuthentication(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws AuthenticationException, IOException;

  public abstract void onSuccessfulAuthentication(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Authentication paramAuthentication)
    throws IOException;

  public abstract void onUnsuccessfulAuthentication(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, AuthenticationException paramAuthenticationException)
    throws IOException;
}
