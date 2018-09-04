package com.walmart.scm.framework.services.rest.util;

import com.google.common.base.Throwables;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class PerformanceLogger
{
  private static final Logger logger = LoggerFactory.getLogger(PerformanceLogger.class);
  
  @Pointcut("@annotation(loggable)")
  public void annotationPointCutDefinition(Loggable loggable) {}
  
  @Pointcut("execution(* com.spglobal.ratings.services..*.*(..))")
  public void atExecution() {}
  
  @Around("annotationPointCutDefinition(loggable) && atExecution()")
  public Object around(ProceedingJoinPoint point, Loggable loggable)
    throws Throwable
  {
    long startTime = System.nanoTime();
    String status = "OK";
    Object result = null;
    try
    {
      result = point.proceed();
    }
    catch (Throwable e)
    {
      long elapsedTime;
      status = "ERROR";
      if (loggable.exceptionMode()) {
        LogUtils.logException(logger, loggable.group(), point.getTarget().getClass().getSimpleName(), ((MethodSignature)MethodSignature.class.cast(point.getSignature())).getMethod().getName(), Arrays.toString(point.getArgs()), Throwables.getStackTraceAsString(e));
      }
      throw e;
    }
    finally
    {
      if (loggable.performanceMode())
      {
        long elapsedTime = System.nanoTime() - startTime;
        LogUtils.logPerformance(logger, status, loggable.group(), point.getTarget().getClass().getSimpleName(), ((MethodSignature)MethodSignature.class.cast(point.getSignature())).getMethod().getName(), Arrays.toString(point.getArgs()), elapsedTime);
      }
    }
    return result;
  }
}
