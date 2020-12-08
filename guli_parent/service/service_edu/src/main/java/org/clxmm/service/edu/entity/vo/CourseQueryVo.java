package org.clxmm.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程查询的实体类
 * @author clxmm
 * @version 1.0
 * @date 2020/12/7 8:09 下午
 */
@Data
public class CourseQueryVo implements Serializable {

    private String title;

    private String teacherId;

    private String subjectParentId;

    private String subjectId;


}
