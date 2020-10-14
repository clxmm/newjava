package org.clxmm.springboot08datajpa.repository;

import org.clxmm.springboot08datajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/14 8:06 下午
 */
// 继承 JpaRepository 完成对数据哭的操作
public interface UserRepository  extends JpaRepository<User,Integer> {





}
