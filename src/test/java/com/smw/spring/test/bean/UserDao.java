package com.smw.spring.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

  private static final Map<String, String> map = new HashMap<>();

  static {
    map.put("10001", "smw");
    map.put("10002", "smww");
    map.put("10003", "smwww");
  }

  public String queryUserName(String uId) {
    return map.get(uId);
  }


}
