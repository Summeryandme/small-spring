package com.smw.spring.test.bean;

public class UserService {

  private String uId;

  private Integer age;

  private UserDao userDao;

  public UserService() {
  }

  public UserService(Integer age) {
    this.age = age;
  }

  public UserService(String uId) {
    this.uId = uId;
  }

  public UserService(String uId, Integer age) {
    this.uId = uId;
    this.age = age;
  }

  public void queryUserInfo() {
    System.out.println("查询用户信息： " + userDao.queryUserName(uId));
  }

}
