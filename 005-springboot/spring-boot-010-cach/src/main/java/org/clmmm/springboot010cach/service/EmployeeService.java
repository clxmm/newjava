package org.clmmm.springboot010cach.service;

import org.clmmm.springboot010cach.bean.Employee;
import org.clmmm.springboot010cach.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 5:24 下午
 */
@Service
/**
 * 抽取缓存中的公共配置
 */
@CacheConfig(cacheNames = "emp")
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    /**
     *  将方法的运行结果进行缓冲，以后再有相同的数据，直接从缓存中获取，不调用方法；
     *  cacheManager 管理多个缓存组件的，对缓存的真正crud操作在cache组件中，每一个缓存组件都有自己唯一一个名字
     *
     *  几个属性：
     *      cacheNames/value : 指定缓存组件的名字，可以是多个
     *      key ： 缓存数据时用到的key；默认使用方法参数的值    值是方法的值，可以是 spel 表达式，
     *                  如 #id 参数id的值  #a0 #p0 #roo.args[0]
     *      keyGenerator: key的生成器，也可以自定义  与key 二选一
     *      cacheManager： 缓存管理器
     *      condition： 指定符合条件的情况下   spel 表达式   #id>0
     *      unless: 否定缓存： 条件为true  方法的缓存值不被缓存 获取结果进行判断可以 #result == null
     *      sync: 是否使用一步
     *原理：
     *  1、 自动配置类，：CacheAutoConfiguration
     *  2、 导入缓存的配置类
     *       static class CacheConfigurationImportSelector implements ImportSelector {
     *
     *                @Override
     *        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
     * 			CacheType[] types = CacheType.values();
     * 			String[] imports = new String[types.length];
     * 			for (int i = 0; i < types.length; i++) {
     * 				imports[i] = CacheConfigurations.getConfigurationClass(types[i]);
     *            }
     * 			return imports;
     *        }
     *    * 	}
     *    3、 那些配置生效 默认的SimpleCacheConfiguration;
     *    4、 给容器中组册了一个 CacheManager ：ConcurrentMapCacheManager
     *    5、 可以创建和获取ConcurrentMapCacheManager类型的缓存组件； 数据保存在  ConcurrentMapCache  private final ConcurrentMap<Object, Object> store 中;
     *
     *Cacheable
     *  运行流程
     *  1、 方法运行之前，查询cache中有没有缓存组件，按照cacheNames指定的名字获取；
     *      （CacheManager先获取相应的缓存，）第一次获取缓存如果没有相应的组件，cache组件会自动创建，
     *  2、 去cache中查询缓存的内容，使用一个key 默认为方法的参数，
     *      key的生成策略，simpleKeyGenerator 生成key
     *      simpleKeyGenerator key的生成策略
     *          没有参数： key = new simpekey
     *          一个参数 key = 参数值
     *          多个参数 key = new SimpleKey(params);
     *  3、 没有查到缓存，调用目标方法，把内容放到缓存中
     *
     * @Cacheable 标注的方法执行之前先检查缓存中有没有这个数据，默认是按照参数的值作为key、去查询的，如果没有，就运行方法，并把结果放入缓存中;
     * 以后在来调用就可以直接使用缓存中的数据
     *0
     * 核心：
     *  1） 使用CacheManager （ConcurrentMapCacheManager） 按照名字得到cache （ConcurrentMapCache） 组件
     *  2） key使用simpleKeyGenerator 生成
     *
     */
//    @Cacheable(cacheNames = "emp",keyGenerator = "myKeyGenerator")
    @Cacheable(cacheNames = "emp")

    public Employee getEmp(Integer id) {
        System.out.println("查询 ：" +id + "员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * @CachePut: 即调用方法，有更新缓存
     *  修改了数据库的某个数据，同时更新缓存
     *
     *  运行时机：
     *      1、先调用目标方法
     *      2、将目标方法结果缓存起来
     *  key 的值要一样 和查询的key要一样
     *      #employee.id  或者 #resulut.id
     *      这样跟新时才能覆盖掉缓存的数据
     *
     */
    @CachePut( value = "emp",key = "#employee.id")
    public Employee updateEmp(Employee employee) {


        System.out.println("updateEmp" + employee);
        employeeMapper.updateEmployee(employee);

        return employee;
    }


    /**
     * @CacheEvict: 缓存清除
     * allEntries = true : 指定清除所有的缓存   默认false
     * beforeInvocation = true  : 在方法之前执行     默认false
     *  默认是false： 清除操作是在方法执行之后，出现异常就不会清除；
     *  改为true： 清除操作是在方法执行之前，无论方法2是否异常，都会清除
     *
     */
    @CacheEvict(value = "emp",beforeInvocation = true)
    public void deleteEmp(Integer id) {

        System.out.println("deleteEmp  " + id);
//        employeeMapper.deleteEmpById(id);
    }


    /**
     * 定义2复杂的缓存规则·
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email"),
            }
    )
    public Employee getEmpByLastName(String lastName) {
        System.out.println("getEmpByLastName " + lastName);
        return  employeeMapper.getEmpByLastName(lastName);
    }


}
