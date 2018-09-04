package com.walmart.scm.framework.services.rest.util;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface Loggable
{
  String group() default "";
  
  boolean performanceMode() default true;
  
  boolean exceptionMode() default true;
}
