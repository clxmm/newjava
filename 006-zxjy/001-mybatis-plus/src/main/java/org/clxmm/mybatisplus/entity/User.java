package org.clxmm.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/1 10:34 下午
 */
@Data
public class User {

    @TableId(type = IdType.ASSIGN_ID)   //默认的策略
//    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;

}
