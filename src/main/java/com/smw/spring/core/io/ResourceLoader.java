package com.smw.spring.core.io;

public interface ResourceLoader {

  String CLASSPATH_URL_PREFIX = "classpath:";
  Resource getResource(String location);
}