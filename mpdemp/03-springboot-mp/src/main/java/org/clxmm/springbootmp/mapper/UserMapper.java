package org.clxmm.springbootmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.clxmm.springbootmp.pojo.User;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/21 8:14 下午
 */
public interface UserMapper extends MyBaseMapper<User> {

    User findById(Long id);
}
