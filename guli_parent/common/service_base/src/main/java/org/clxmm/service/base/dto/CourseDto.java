package org.clxmm.service.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/13 1:44 下午
 */
@Data
public class CourseDto implements Serializable {

    private String id;//课程ID
    private String title;//课程标题
    private BigDecimal price;//课程销售价格，设置为0则可免费观看
    private String cover;//课程封面图片路径
    private String teacherName;//课程讲师

}
