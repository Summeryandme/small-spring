package com.smw.spring.beans.factory.config;

import com.smw.spring.beans.PropertyValues;
import java.util.Objects;

public class BeanDefinition {

  private static final String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
  private static final String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

  private Class<?> beanClass;

  private PropertyValues propertyValues;

  private String initMethodName;

  private String destroyMethodName;

  private String scope = SCOPE_SINGLETON;

  private boolean singleton = true;

  private boolean prototype = false;

  public String getScope() {
    return scope;
  }

  public boolean isSingleton() {
    return Objects.equals(scope, SCOPE_SINGLETON);
  }

  public boolean isPrototype() {
    return Objects.equals(scope, SCOPE_PROTOTYPE);
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getInitMethodName() {
    return initMethodName;
  }

  public void setInitMethodName(String initMethodName) {
    this.initMethodName = initMethodName;
  }

  public String getDestroyMethodName() {
    return destroyMethodName;
  }

  public void setDestroyMethodName(String destroyMethodName) {
    this.destroyMethodName = destroyMethodName;
  }

  public BeanDefinition(Class<?> beanClass) {
    this.beanClass = beanClass;
    this.propertyValues = new PropertyValues();
  }

  public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
    this.beanClass = beanClass;
    this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
  }

  public PropertyValues getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(PropertyValues propertyValues) {
    this.propertyValues = propertyValues;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class<?> beanClass) {
    this.beanClass = beanClass;
  }
}
