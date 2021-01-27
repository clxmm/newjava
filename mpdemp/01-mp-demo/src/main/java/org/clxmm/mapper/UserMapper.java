package org.clxmm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.clxmm.pojo.User;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/20 7:45 下午
 */
public interface UserMapper  extends BaseMapper<User> {
    List<User> findAll();


}
