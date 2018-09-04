package com.walmart.scm.framework.services.rest.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CollectionResult<T>
{
  @XmlElement
  private Long total;
  @XmlElement
  private Long count;
  @XmlElement
  private List<T> result;
  
  public Long getTotal()
  {
    return this.total;
  }
  
  public void setTotal(Long total)
  {
    this.total = total;
  }
  
  public Long getCount()
  {
    return this.count;
  }
  
  public void setCount(Long count)
  {
    this.count = count;
  }
  
  public List<T> getResult()
  {
    return this.result;
  }
  
  public void setResult(List<T> result)
  {
    this.result = result;
  }
}
