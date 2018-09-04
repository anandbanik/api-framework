package com.walmart.scm.framework.services.rest.util;

import java.sql.Connection;
import net.sf.log4jdbc.ConnectionSpy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DataSourceInterceptor
{
  @Pointcut("execution(* javax.sql.DataSource.getConnection())")
  public void getConnection() {}
  
  @Around("com.spglobal.ratings.framework.services.rest.util.DataSourceInterceptor.getConnection()")
  public Object getConnection(ProceedingJoinPoint pjp)
    throws Throwable
  {
    return new ConnectionSpy((Connection)pjp.proceed());
  }
}
