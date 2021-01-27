package org.clxmm.springbootmp.sqlInjector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.clxmm.springbootmp.config.FindAll;


import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/26 7:16 下午
 */
public class MySqlInjector extends DefaultSqlInjector {


    @Override
    public List<AbstractMethod> getMethodList() {
        // 获取原来的扩展
        List<AbstractMethod> methodList = super.getMethodList();
        methodList.add(new FindAll());

        return methodList;
    }
}
