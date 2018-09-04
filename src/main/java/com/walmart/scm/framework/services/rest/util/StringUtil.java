package com.walmart.scm.framework.services.rest.util;

public class StringUtil
{
  public static boolean isValid(String str)
  {
    return (null != str) && (str.trim().length() > 0);
  }
}
