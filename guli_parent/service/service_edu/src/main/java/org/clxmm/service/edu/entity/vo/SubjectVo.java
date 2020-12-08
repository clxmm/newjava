package org.clxmm.service.edu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/30 8:43 下午
 */
@Data
public class SubjectVo {

    private String id;

    private String title;

    private Integer sort;

    // 单向自关联
    private List<SubjectVo> children = new ArrayList<>();


}
