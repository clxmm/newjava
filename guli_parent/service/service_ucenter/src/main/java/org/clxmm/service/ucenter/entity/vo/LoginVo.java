package org.clxmm.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/8 6:12 下午
 */
@Data
public class LoginVo implements Serializable {

    private String mobile;

    private String password;
}
