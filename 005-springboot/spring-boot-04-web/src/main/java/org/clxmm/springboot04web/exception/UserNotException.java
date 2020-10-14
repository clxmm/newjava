package org.clxmm.springboot04web.exception;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/11 3:57 下午
 */
public class UserNotException extends RuntimeException {


    public UserNotException() {
        super("用户不存在");
    }
}
