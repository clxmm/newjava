package i;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.实例化bean
 * 2。 保存bean
 * 3。提供bean
 * 4。 每个bean的id都是唯一的
 *
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 2:56 下午
 */
public class IocContainer {

    private Map<String,Object> beans = new ConcurrentHashMap<String, Object>();


    /**
     * 根据bean ID 获取bean
     * @param id
     * @return
     */
    public Object getBean(String id) {
        return beans.get(id);
    }


    /**
     *  委托ioc 创建一个bean
     * @param clazz
     * @param beanId
     * @param paramBaenIds 所需依赖的bean id
     */
    public void setBean(Class<?> clazz, String beanId,String... paramBaenIds) {
        // 1。 组装构造方法所需要的参数值.
        Object[] paramValues = new Object[paramBaenIds.length];
        for (int i = 0; i < paramBaenIds.length; i++) {
            paramValues[i] = beans.get(paramBaenIds[i]);
        }

        // 2。 调用构造方法实例化bean
        Object bean = null;

        for (Constructor<?> constructor : clazz.getConstructors()) {

            try {
                bean = constructor.newInstance(paramValues);
            } catch (InstantiationException e) {
//                e.printStackTrace();
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
            } catch (InvocationTargetException e) {
//                e.printStackTrace();
            }

            if (bean == null) {
                throw new RuntimeException("找不到合适的构造方法去实例化bean");
            }

        }

        // 3。 实例化的bean放入map
        beans.put(beanId,bean);

    }













}
