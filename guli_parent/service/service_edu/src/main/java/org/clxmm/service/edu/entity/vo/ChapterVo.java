package org.clxmm.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/14 8:29 下午
 */
@Data
public class ChapterVo implements Serializable {
    private String id;

    private String title;

    private Integer sort;

    private List<VideoVo> children = new ArrayList<>();

}
