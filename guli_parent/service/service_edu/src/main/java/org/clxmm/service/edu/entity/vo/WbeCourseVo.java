package org.clxmm.service.edu.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/7 7:38 下午
 */
@Data
public class WbeCourseVo {

    private static final long serialVersionUID = 1L;
    private String id;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String description;
    private String teacherId;
    private String teacherName;
    private String intro;
    private String avatar;
    private String subjectLevelOneId;
    private String subjectLevelOne;
    private String subjectLevelTwoId;
    private String subjectLevelTwo;

}
