package org.clxmm.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.service.edu.entity.form.CourseInfoForm;
import org.clxmm.service.edu.entity.vo.CourseQueryVo;
import org.clxmm.service.edu.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseFormById(String id);

    /**
     * 更具id更新课程信息
     * @param courseInfoForm
     * @return
     */
    String updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseVo> pageSelect(Page<CourseVo> page, CourseQueryVo courseQueryVo);
}
