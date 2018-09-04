package com.walmart.scm.framework.services.rest.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;

public class FieldUtil
{
  private static final List<String> IGNORE_FIELDS = Arrays.asList(new String[] { "serialVersionUID" });
  
  public static void setFields(Object original, List<String> fields)
  {
    if ((original == null) || (fields == null) || (fields.size() == 0)) {
      return;
    }
    List<Field> originalFields = getAllFields(new ArrayList(), original.getClass());
    List<String> adjustedFields = new ArrayList(fields);
    Map<String, List<String>> nestedMap = new HashMap();
    for (String name : fields) {
      if (name.indexOf(".") > 0)
      {
        String[] result = name.split("\\.");
        String fieldName = result[0];
        if (!nestedMap.containsKey(fieldName))
        {
          nestedMap.put(fieldName, new ArrayList());
          adjustedFields.add(fieldName);
        }
        ((List)nestedMap.get(fieldName)).add(result[1]);
      }
    }
    for (String key : nestedMap.keySet()) {
      try
      {
        Field f = original.getClass().getDeclaredField(key);
        f.setAccessible(true);
        
        Object object = f.get(original);
        setFields(object, (List)nestedMap.get(key));
      }
      catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException localNoSuchFieldException) {}
    }
    for (Field field : originalFields) {
      if ((!adjustedFields.contains(field.getName())) && (!IGNORE_FIELDS.contains(field.getName()))) {
        try
        {
          PropertyUtils.setProperty(original, field.getName(), null);
        }
        catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException localIllegalAccessException) {}
      }
    }
  }
  
  private static <T> List<Field> getAllFields(List<Field> fields, Class<T> type)
  {
    for (Field field : type.getDeclaredFields()) {
      fields.add(field);
    }
    if (type.getSuperclass() != null) {
      fields = getAllFields(fields, type.getSuperclass());
    }
    return fields;
  }
}
