package org.clxmm.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.service.edu.entity.Course;
import org.clxmm.service.edu.entity.CourseDescription;
import org.clxmm.service.edu.entity.form.CourseInfoForm;
import org.clxmm.service.edu.entity.vo.CourseQueryVo;
import org.clxmm.service.edu.entity.vo.CourseVo;
import org.clxmm.service.edu.mapper.CourseDescriptionMapper;
import org.clxmm.service.edu.mapper.CourseMapper;
import org.clxmm.service.edu.service.CourseDescriptionService;
import org.clxmm.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CourseDescriptionMapper courseDescriptionMapper;

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


}
