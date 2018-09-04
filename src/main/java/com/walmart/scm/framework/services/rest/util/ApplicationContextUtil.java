package com.walmart.scm.framework.services.rest.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil
  implements ApplicationContextAware
{
  @Autowired
  private static ApplicationContext applicationContext;
  
  public static Object getBean(String beanName)
  {
    return applicationContext.getBean(beanName);
  }
  
  public void setApplicationContext(ApplicationContext arg0)
    throws BeansException
  {
    applicationContext = arg0;
  }
}
