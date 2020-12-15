package org.clxmm.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/14 8:26 下午
 */
@Data
public class VideoVo implements Serializable {

    private String id;

    private String title;

    private Boolean free;

    private Integer sort;

    private String videoSourceId;

}
