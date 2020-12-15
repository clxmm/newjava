package org.clxmm.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.service.edu.entity.form.CourseInfoForm;
import org.clxmm.service.edu.entity.vo.CoursePublishVo;
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

    /**
     * 删除课程的封面
     * @param id
     * @return
     */
    boolean removeCoverById(String id);

    /**
     * 根据课程id的删除课程 和相关的  课时信息吗 课程 收藏 评论
     * @param id
     * @return
     */
    boolean removeCourseById(String id);

    /**
     * ID获取课程发布信息
     * @param id
     * @return
     */
    CoursePublishVo getCoursePublishVoById(String id);

    /**
     *  根据课程id发布课程
     * @param id 课程id
     * @return
     */
    Boolean publishCourseById(String id);
}
