package com.smw.spring.test.bean;

public class UserService {

  private String name;

  private Integer age;

  public UserService() {
  }

  public UserService(Integer age) {
    this.age = age;
  }

  public UserService(String name) {
    this.name = name;
  }

  public UserService(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public void queryUserInfo() {
    System.out.println("姓名" + name + "年龄" + age);
  }

}
