package org.clxmm.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/3 7:39 下午
 */
@Data
public class RegisterVo implements Serializable {
    private String nickname;

    private String mobile;

    private String password;

    private String code;


}
