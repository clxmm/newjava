package org.clxmm.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.clxmm.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.clxmm.service.edu.entity.vo.CoursePublishVo;
import org.clxmm.service.edu.entity.vo.CourseVo;
import org.clxmm.service.edu.entity.vo.WbeCourseVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> selectPageByCourseQueryVo(
            //mp会自动组装分页参数
            Page<CourseVo> pageParam,
            //mp会自动组装queryWrapper：
            //@Param(Constants.WRAPPER) 和 xml文件中的 ${ew.customSqlSegment} 对应
            @Param(Constants.WRAPPER) QueryWrapper<Course> queryWrapper);

    CoursePublishVo getCoursePublishVoById(String id);

    WbeCourseVo selectWebCourseVoById(String courseId);
}
