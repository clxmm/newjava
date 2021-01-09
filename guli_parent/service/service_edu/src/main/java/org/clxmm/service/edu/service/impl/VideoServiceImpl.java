package org.clxmm.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.service.edu.entity.Video;
import org.clxmm.service.edu.fegin.VodMediaService;
import org.clxmm.service.edu.mapper.VideoMapper;
import org.clxmm.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@Service
@Slf4j
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    VodMediaService vodMediaService;

    @Override
    public void removeMediaVideoById(String id) {
        Video video = baseMapper.selectById(id);
        log.info("远程方法：删除视频ID" +video.getVideoSourceId());
        if (StringUtils.isNotBlank(video.getVideoSourceId())) {
            log.info("远程方法：删除视频ID" +video.getVideoSourceId());
            //  调用远程方法删除阿里云上的视频
            vodMediaService.removeVideo(video.getVideoSourceId());
        }

    }

    @Override
    public void removeMediaVideoByChapterId(String chapterId) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Video::getVideoSourceId)
                .eq(Video::getChapterId,chapterId);
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        ArrayList<String> ids = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            String videoSourceId = (String) map.get("video_source_id");
            ids.add(videoSourceId);
        }
        log.info("要删除的视频ids" + ids);
        vodMediaService.removeVideoByIdList(ids);
    }

    @Override
    public void removeMediaVideoByCourseId(String courseId) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Video::getVideoSourceId)
                .eq(Video::getCourseId,courseId);
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        ArrayList<String> ids = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            String videoSourceId = (String) map.get("video_source_id");
            ids.add(videoSourceId);
        }
        log.info("要删除的视频ids" + ids);
        vodMediaService.removeVideoByIdList(ids);
    }


}
