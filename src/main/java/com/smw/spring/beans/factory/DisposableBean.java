package com.smw.spring.beans.factory;

public interface DisposableBean {

  void destroy() throws Exception;

}