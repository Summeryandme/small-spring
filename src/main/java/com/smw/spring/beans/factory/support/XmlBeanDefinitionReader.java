package com.smw.spring.beans.factory.support;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.XmlUtil;
import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.PropertyValue;
import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.config.BeanReference;
import com.smw.spring.core.io.Resource;
import com.smw.spring.core.io.ResourceLoader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

  public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
    super(beanDefinitionRegistry);
  }

  public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry,
      ResourceLoader resourceLoader) {
    super(beanDefinitionRegistry, resourceLoader);
  }

  @Override
  public void loadResourceDefinition(Resource resource) {

    try (InputStream inputStream = resource.getInputStream()) {
      doLoadBeanDefinition(inputStream);
    } catch (IOException | ClassNotFoundException e) {
      throw new BeansException("IOException parsing XML document from " + resource, e);
    }
  }

  @Override
  public void loadResourceDefinition(Resource... resources) {
    Arrays.stream(resources).forEach(this::loadResourceDefinition);
  }

  @Override
  public void loadResourceDefinition(String location) {
    loadResourceDefinition(getResourceLoader().getResource(location));
  }

  @Override
  public void loadResourceDefinition(String... locations) {
    Arrays.stream(locations).forEach(this::loadResourceDefinition);

  }

  private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException {
    Document document = XmlUtil.readXML(inputStream);
    Element root = document.getDocumentElement();
    NodeList childNodes = root.getChildNodes();
    for (int i = 0; i < childNodes.getLength(); i++) {
      if (!(childNodes.item(i) instanceof Element && "bean".equals(
          childNodes.item(i).getNodeName()))) {
        continue;
      }
      Element bean = (Element) childNodes.item(i);
      String id = bean.getAttribute("id");
      String name = bean.getAttribute("name");
      String className = bean.getAttribute("class");
      String initMethodName = bean.getAttribute("init-method");
      String destroyMethodName = bean.getAttribute("destroy-method");
      String beanScope = bean.getAttribute("scope");
      Class<?> clazz = Class.forName(className);
      // 优先级 id > name
      String beanName = CharSequenceUtil.isNotEmpty(id) ? id : name;
      if (CharSequenceUtil.isEmpty(beanName)) {
        beanName = CharSequenceUtil.lowerFirst(clazz.getSimpleName());
      }
      BeanDefinition beanDefinition = new BeanDefinition(clazz);
      beanDefinition.setInitMethodName(initMethodName);
      beanDefinition.setDestroyMethodName(destroyMethodName);
      if (CharSequenceUtil.isNotEmpty(beanScope)) {
        beanDefinition.setScope(beanScope);
      }
      populateProperties(beanDefinition, bean.getChildNodes());

      BeanDefinitionRegistry beanDefinitionRegistry = getRegistry();
      if (beanDefinitionRegistry.containsBeanDefinition(beanName)) {
        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
      }
      beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
    }
  }

  private void populateProperties(BeanDefinition beanDefinition, NodeList beanChildNodes) {
    for (int j = 0; j < beanChildNodes.getLength(); j++) {
      Node item = beanChildNodes.item(j);
      if (!(item instanceof Element) || !"property".equals(item.getNodeName())) {
        continue;
      }
      Element property = (Element) item;
      String attrName = property.getAttribute("name");
      String attrValue = property.getAttribute("value");
      String attrRef = property.getAttribute("ref");
      Object value =
          CharSequenceUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
      PropertyValue propertyValue = new PropertyValue(attrName, value);
      beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
    }
  }

}
