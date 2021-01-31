package org.clxmm.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.*;
import org.clxmm.service.edu.entity.form.CourseInfoForm;
import org.clxmm.service.edu.entity.vo.*;
import org.clxmm.service.edu.fegin.OssFileService;
import org.clxmm.service.edu.mapper.*;
import org.clxmm.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    OssFileService ossFileService;
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CourseCollectMapper courseCollectMapper;


    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        // 保存course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.insert(course);

        // 保存 CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());

        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseFormById(String id) {

        Course course = baseMapper.selectById(id);
        if (course == null) {
            return null;
        }

        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        //创建courseInfoForm对象
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public String updateCourseInfoById(CourseInfoForm courseInfoForm) {

        // 保存course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.updateById(course);

        // 保存 CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());

        courseDescriptionMapper.updateById(courseDescription);

        return course.getId();
    }

    @Override
    public IPage<CourseVo> pageSelect(Page<CourseVo> page, CourseQueryVo courseQueryVo) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc(" c .gmt_create ");  //
        String title = courseQueryVo.getTitle();
        String teacherId = courseQueryVo.getTeacherId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();

        if (StringUtils.isNotEmpty(title)) {
            queryWrapper.like("c.title", title);
        }

        if (StringUtils.isNotBlank(teacherId)) {
            queryWrapper.eq("c.teacher_id", teacherId);
        }

        if (StringUtils.isNotBlank(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }

        if (StringUtils.isNotBlank(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }


        //放入分页参数和查询条件参数，mp会自动组装
        List<CourseVo> records = baseMapper.selectPageByCourseQueryVo(page, queryWrapper);
        page.setRecords(records);
        return page;

    }

    /**
     * @param id 课程id
     * @return
     */
    @Override
    public boolean removeCoverById(String id) {

        Course course = baseMapper.selectById(id);

        if (course != null && StringUtils.isNotBlank(course.getCover())) {
            R r = ossFileService.removeFile(course.getCover());
            return r.getSuccess();
        }

        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeCourseById(String id) {

        // 收藏信息
        QueryWrapper<CourseCollect> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper.eq("course_id", id);
        courseCollectMapper.delete(collectQueryWrapper);


        // 评论信息
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getCourseId, id);
        commentMapper.delete(commentLambdaQueryWrapper);

        // 课时信息
        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getCourseId, id);
        videoMapper.delete(videoLambdaQueryWrapper);

        // 章节信息
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, id);
        chapterMapper.delete(chapterLambdaQueryWrapper);

        //课程详情
        courseDescriptionMapper.deleteById(id);

        // 课程信息
        this.removeById(id);

        return true;
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {


        return baseMapper.getCoursePublishVoById(id);
    }

    @Override
    public Boolean publishCourseById(String id) {
        Course course = new Course();
        course.setStatus(Course.COURSE_NORMAL);
        course.setId(id);
        return this.updateById(course);
    }

    @Override
    public List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        // 查询已发布的课程

        queryWrapper.eq("status", Course.COURSE_NORMAL);


        if (!StringUtils.isEmpty(webCourseQueryVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", webCourseQueryVo.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(webCourseQueryVo.getSubjectId())) {
            queryWrapper.eq("subject_id", webCourseQueryVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(webCourseQueryVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(webCourseQueryVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(webCourseQueryVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public WbeCourseVo selectWebCourseVoById(String courseId) {
        //更新课程浏览数
        Course course = baseMapper.selectById(courseId);
        course.setViewCount(course.getVersion() + 1);
        baseMapper.updateById(course);

        return baseMapper.selectWebCourseVoById(courseId);


    }


    @Cacheable(value = "index", key = "'selectHotCourse'")
    @Override
    public List<Course> selectHotCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }


}
