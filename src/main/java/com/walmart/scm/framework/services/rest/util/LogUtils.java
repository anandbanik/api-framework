package com.walmart.scm.framework.services.rest.util;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;

public class LogUtils
{
  public static void logPerformance(Logger logger, String status, String logGroupName, String logClassName, String logMethodName, String logParams, long elapsedTime)
  {
    logger.info(String.format("%-15s : %-5s : %3d min, %3d sec, %3d millisec, %3d microsec - %s.%s(%s)", new Object[] { logGroupName, status, 
    
      Long.valueOf(TimeUnit.NANOSECONDS.toMinutes(elapsedTime)), 
      Long.valueOf(TimeUnit.NANOSECONDS.toSeconds(elapsedTime) - TimeUnit.NANOSECONDS.toMinutes(elapsedTime) * 60L), 
      Long.valueOf(TimeUnit.NANOSECONDS.toMillis(elapsedTime) - TimeUnit.NANOSECONDS.toSeconds(elapsedTime) * 1000L), 
      Long.valueOf(TimeUnit.NANOSECONDS.toMicros(elapsedTime) - TimeUnit.NANOSECONDS.toMillis(elapsedTime) * 1000L), logClassName, logMethodName, logParams }));
  }
  
  public static void logException(Logger logger, String logGroupName, String logClassName, String logMethodName, String logParams, String exceptionMessage)
  {
    logger.error(String.format("%-15s - %s.%s(%s) : \n %s", new Object[] { logGroupName, logClassName, logMethodName, logParams, exceptionMessage }));
  }
}