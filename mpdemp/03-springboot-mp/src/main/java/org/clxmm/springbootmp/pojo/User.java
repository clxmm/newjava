package org.clxmm.springbootmp.pojo;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/21 8:16 下午
 */

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.clxmm.springbootmp.enums.SexEnum;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User extends Model<User> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    // 插数据时填充 ，
    @TableField(select = false,fill = FieldFill.INSERT) // 不查询
    private String password;
    private String name;

    private Integer age;

    @TableField(value = "email")
    private String mail;


    @TableField(exist = false)
    private String address;

    @Version
    private Integer version;

    @TableLogic
     private Integer deleted;

    private SexEnum sex;


}