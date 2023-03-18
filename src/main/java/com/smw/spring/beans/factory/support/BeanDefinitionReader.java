package com.smw.spring.beans.factory.support;

import com.smw.spring.core.io.Resource;
import com.smw.spring.core.io.ResourceLoader;

public interface BeanDefinitionReader {

  BeanDefinitionRegistry getRegistry();

  ResourceLoader getResourceLoader();

  void loadResourceDefinition(Resource resource);

  void loadResourceDefinition(Resource... resources);

  void loadResourceDefinition(String location);
  void loadResourceDefinition(String... locations);


}
