
#### 1 ioc 
* ioc: 控制反转 inversion of control 
* 1. 控制什么？
    控制对象的创建及销毁（生命周期）
* 2。反转什么？
    将对象的控制权交给ioc容器
    
#### 实现自己的ioc 容器

约定
* 所有的Bean的生命周期交由ioc容器管理
* 所有被依赖的bean通过构造方法执行注入
* 被依赖的bean需要优先创建


1. 所有的依赖关系被集中的管理起来
2. 每个类只需要关心自己的业务逻辑


#### spring ioc 的容器

### 实例化的bean 的方式
* 通过构造方法
* 通过静态方法
* 通过实例方法
* Bean的别名 



### 注入bean的方式
* 通过构造方法注入bean
* 通过set方法注入bean
* 集合bean的型注入  list set map properties
* null 值注入
* 注入时创建内部bean 



### Bean 的作用域
* Singleton 作用域
在一个上下文环境同一个实例是相同的

* prototype

bean1 是 Singleton bean2 是prototype 
每次调用bean1的某个方法，拿到的bean2都是一个新的实例 ？ 
------》 方法注入  <lookup-method />



* web环境作用域 request session application websocket
* 自定义作用域 SimpleThreadScope   implements Scope



###  bean 的懒加载
<bean id="baen" class="com.Bean" lazy-init="true"/>

默认情况下bean 是在创建容器时提前初始化 

只针对singleton   作用域的bean


#### bean的初始化及销毁逻辑
bean的创建
1. init-method
2. 让 bean实现 InitializingBean

bean 的销毁
1. destroy-method
2. DisposableBean

全局配置默认的方法
```
     default-init-method="init"
       default-destroy-method="destroy"
```

#### bean 属性继承

1。 通过父类的继承 ，继承父的bean

2。 直接继承相同的bean


#### 14 注解的基本使用

给bean起别名 
 只能是@Bean注解
    
    public @interface Bean {
	@AliasFor("name")
	String[] value() default {};
	
#### 15通过注解去注入bean
* 通过方法注入bean   构造方法，set方法
* 通过属性注入bean
* 集合bean的型注入  直接注入集合实例；将多个泛型的实例注入到集合 （将多个泛型的实例注入到list，控制泛型实例在list中的实例，将多个泛型的实例注入到map）
* String ，Integer 等类型直接赋值
* springIoc 容器内置接口注入实例 *



#### 通过注解设定bean的作用域

* Singleton 作用域
* prototype作用域
* web环境作用域  request session application websocket
* 自定义作用域


### 16 通过注解实现懒加载
@Lazy  

### 17 通过注解的方式实现bean的初始化和销毁

1. implements InitializingBean, DisposableBean
2. 
@PostConstruct
    public void onInit() {
    

@PreDestroy
public void onDestory() {
    
    
3.  @Bean(initMethod = "onInit1",destroyMethod = "onDestory1")   // @√ΩComponent  没有这两个属性












