package org.clxmm.service.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/13 1:45 下午
 */
@Data
public class MemberDto implements Serializable {

    private String id;//会员id
    private String mobile;//手机号
    private String nickname;//昵称

}
