package org.clxmm.service.edu.entity.vo;

import lombok.Data;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/7 8:12 下午
 */
@Data
public class CourseVo {
    private String id;

    private String title;

    private String subjectParentTitle;
    private String subjectTitle;

    private String teacherName;
    private Integer lessonNum;

    private String price;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private String status;

    private String gmtCreate;


}
