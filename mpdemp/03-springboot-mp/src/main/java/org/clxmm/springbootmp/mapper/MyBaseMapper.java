package org.clxmm.springbootmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/26 7:13 下午
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    List<T> findAll();


}
