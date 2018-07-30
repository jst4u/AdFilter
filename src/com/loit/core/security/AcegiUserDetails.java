package com.loit.core.security;

import java.util.List;
import org.springframework.security.userdetails.UserDetails;

public abstract interface AcegiUserDetails extends UserDetails
{
  public abstract String getUserId();

  public abstract String getFullname();

  public abstract List<String> getGrantedUrls();

  public abstract List<String> getForbiddenUrls();
}
