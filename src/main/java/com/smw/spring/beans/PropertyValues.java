package com.smw.spring.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyValues {

  private final List<PropertyValue> propertyValueList = new ArrayList<>();

  public void addPropertyValue(PropertyValue propertyValue) {
    propertyValueList.add(propertyValue);
  }

  public PropertyValue getPropertyValue(String propertyName) {
    Optional<PropertyValue> propertyValue = propertyValueList.stream()
        .filter(property -> propertyName.equals(property.getName()))
        .findAny();
    return propertyValue.orElse(null);
  }

  public PropertyValue[] getPropertyValues() {
    return this.propertyValueList.toArray(new PropertyValue[0]);
  }

}
