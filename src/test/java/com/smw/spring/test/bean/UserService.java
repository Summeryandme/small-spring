package com.smw.spring.test.bean;

import com.smw.spring.beans.factory.BeanClassLoaderAware;
import com.smw.spring.beans.factory.BeanFactory;
import com.smw.spring.beans.factory.BeanFactoryAware;
import com.smw.spring.beans.factory.BeanNameAware;
import com.smw.spring.context.ApplicationContext;
import com.smw.spring.context.ApplicationContextAware;

public class UserService implements BeanNameAware,
    BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

  private String uId;

  private String company;

  private String location;

  private UserDao userDao;

  private ApplicationContext applicationContext;

  private BeanFactory beanFactory;

  public UserService() {
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  public String queryUserInfo() {
    return userDao.queryUserName(uId) + "," + company + "," + location;
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    System.out.println("classLoader: " + classLoader);
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  @Override
  public void setBeanName(String name) {
    System.out.println("bean name: " + name);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public BeanFactory getBeanFactory() {
    return beanFactory;
  }
}
