package com.smw.spring.core.io;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource {

  private final String path;
  private final ClassLoader classLoader;

  public ClassPathResource(String path) {
    this(path, null);
  }

  public ClassPathResource(String path, ClassLoader classLoader) {
    Assert.notNull(path, "Path must not be null");
    this.path = path;
    this.classLoader = (classLoader != null ? classLoader : ClassUtil.getClassLoader());
  }

  @Override
  public InputStream getInputStream() throws IOException {
    InputStream inputStream = classLoader.getResourceAsStream(path);
    if (inputStream == null) {
      throw new FileNotFoundException(this.path + " can not be opened because it does not exist");
    }
    return inputStream;
  }
}
