package org.clxmm.springbootmp.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/26 7:51 下午
 */
@Component
public class MyMetaObjectHandler  implements MetaObjectHandler {

    // 插入数据时填充
    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取到password 的值  为空则插入值

        Object password = getFieldValByName("password", metaObject);
        if (null == password) {
            setFieldValByName("password", "123456", metaObject);
        }

    }

    // 更新时
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
