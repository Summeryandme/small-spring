# small-spring
A simple Spring

1. 实现了一个简单的 Bean 容器
2. 运用相关的设计模式，实现 Bean 的定义、注册、获取
3. 基于 Cglib 和反射实现含有构造函数的类实例化策略
4. 为 Bean 对象注入属性和依赖 Bean 的功能实现
5. 设计与实现资源加载器，从 xml 中解析和注册 Bean 对象
6. 实现应用上下文，自动识别、资源加载、扩展机制
7. 向虚拟机注册钩子，实现 Bean 对象的初始化和销毁方法
8. 定义标记类型 Aware 接口，实现感知容器对象
9. 关于 Bean 对象作用域以及 FactoryBean 的实现和使用
10. 基于观察者实现，容器事件和事件监听器
11. 基于 JDK 和 Cgli b动态代理，实现AOP核心功能
12. 把 AOP 动态代理，融入到 Bean 的生命周期