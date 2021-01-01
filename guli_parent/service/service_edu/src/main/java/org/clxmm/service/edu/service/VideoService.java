package org.clxmm.service.edu.service;

import org.clxmm.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
public interface VideoService extends IService<Video> {

    void removeMediaVideoById(String id);

    void removeMediaVideoByChapterId(String chapterId);

    /**
     * 根据课程id删除视频
     * @param courseId
     */
    void removeMediaVideoByCourseId(String courseId);
}
