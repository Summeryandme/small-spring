package com.smw.spring.test.bean;

import com.smw.spring.beans.factory.DisposableBean;
import com.smw.spring.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {

  private String uId;

  private String company;

  private String location;

  private UserDao userDao;

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
  public void destroy() {
    System.out.println("执行：UserService.destroy()");
  }

  @Override
  public void afterPropertiesSet() {
    System.out.println("执行：UserService.afterPropertiesSet()");
  }
}
