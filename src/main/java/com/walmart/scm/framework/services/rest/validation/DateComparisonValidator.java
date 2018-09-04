package com.walmart.scm.framework.services.rest.validation;

import com.walmart.scm.framework.services.rest.annotations.DateComparison;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

public class DateComparisonValidator	
  implements ConstraintValidator<DateComparison, Object>
{
  private String date1;
  private String date1IfNull;
  private String op;
  private String date2;
  private String date2IfNull;
  
  public void initialize(DateComparison constraintAnnotation)
  {
    this.date1 = constraintAnnotation.date1();
    this.date1IfNull = constraintAnnotation.date1IfNull();
    this.op = StringUtils.remove(constraintAnnotation.op(), ' ');
    this.date2 = constraintAnnotation.date2();
    this.date2IfNull = constraintAnnotation.date2IfNull();
  }
  
  public boolean isValid(Object value, ConstraintValidatorContext ctx)
  {
    long date1InMs = 0L;
    long date2InMs = 0L;
    try
    {
      Object _date1 = PropertyUtils.getProperty(value, this.date1);
      Object _date2 = PropertyUtils.getProperty(value, this.date2);
      if (_date1 == null) {
        date1InMs = getDefaultValue(this.date1IfNull);
      } else if ((_date1 instanceof Date)) {
        date1InMs = ((Date)_date1).getTime();
      } else if ((_date1 instanceof Calendar)) {
        date1InMs = ((Calendar)_date1).getTimeInMillis();
      }
      if (_date2 == null) {
        date2InMs = getDefaultValue(this.date2IfNull);
      } else if ((_date2 instanceof Date)) {
        date2InMs = ((Date)_date2).getTime();
      } else if ((_date1 instanceof Calendar)) {
        date2InMs = ((Calendar)_date2).getTimeInMillis();
      }
      if ((date1InMs == 0L) || (date2InMs == 0L)) {
        return true;
      }
      if (">".equals(this.op)) {
        return date1InMs > date2InMs;
      }
      if (">=".equals(this.op)) {
        return date1InMs >= date2InMs;
      }
      if ("<".equals(this.op)) {
        return date1InMs < date2InMs;
      }
      if ("<=".equals(this.op)) {
        return date1InMs <= date2InMs;
      }
      if ("==".equals(this.op)) {
        return date1InMs == date2InMs;
      }
    }
    catch (Exception localException) {}
    return true;
  }
  
  private long getDefaultValue(String defaultValue)
    throws ParseException
  {
    if (StringUtils.isBlank(defaultValue)) {
      return 0L;
    }
    String val = StringUtils.remove(defaultValue, ' ');
    if ("now".equalsIgnoreCase(val)) {
      return System.currentTimeMillis();
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    Date d = sdf.parse(defaultValue);
    return d.getTime();
  }
}
