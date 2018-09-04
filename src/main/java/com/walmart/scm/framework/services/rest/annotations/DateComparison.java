package com.walmart.scm.framework.services.rest.annotations;

import com.walmart.scm.framework.services.rest.validation.DateComparisonValidator;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={DateComparisonValidator.class})
@Documented
public @interface DateComparison
{
  String message() default "{date1} must be {op} {date2}";
  
  Class<?>[] groups() default {};
  
  Class<? extends Payload>[] payload() default {};
  
  String date1();
  
  String date1IfNull() default "";
  
  String op();
  
  String date2();
  
  String date2IfNull() default "";
  
  @Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public static @interface List
  {
    DateComparison[] value();
  }
}
