package org.clxmm.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.clxmm.service.edu.entity.Chapter;
import org.clxmm.service.edu.entity.Video;
import org.clxmm.service.edu.entity.vo.ChapterVo;
import org.clxmm.service.edu.entity.vo.VideoVo;
import org.clxmm.service.edu.mapper.ChapterMapper;
import org.clxmm.service.edu.mapper.VideoMapper;
import org.clxmm.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;


    @Override
    public boolean deleteChapterById(String id) {

        // 课时信息
        LambdaQueryWrapper<Video> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Video::getChapterId,id);
        videoMapper.delete(lambdaQueryWrapper);


        // 章节信息
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> nestedList(String courseId) {

        // 组装章节列表 ： List<ChapterVo>
        // 遍历章列表信息： List<Chapter>
        // 遍历List<Chapter> 通过chapter_id查询 List<Video>


        // 2.
        // 获取所有章节信息列表 List<Chapter>
        // course_id 查询 List<video>   sql

        // 组装数据 章节列表 List<ChapterVo>

        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId,courseId);
        chapterLambdaQueryWrapper.orderByAsc(Chapter::getSort,Chapter::getId);
        List<Chapter> chapterList = baseMapper.selectList(chapterLambdaQueryWrapper);


        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getCourseId,courseId);
        videoLambdaQueryWrapper.orderByAsc(Video::getCourseId,Video::getId);
        List<Video> videoList = videoMapper.selectList(videoLambdaQueryWrapper);


        List<ChapterVo> chapterVoList = new ArrayList<>();

        for (Chapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);

            //填充列表数据：Video列表
            List<VideoVo> videoVoList = new ArrayList<>();

            for (Video video : videoList) {
                if (chapter.getId().equals(video.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }

            chapterVo.setChildren(videoVoList);
        }

        return chapterVoList;
    }
}
