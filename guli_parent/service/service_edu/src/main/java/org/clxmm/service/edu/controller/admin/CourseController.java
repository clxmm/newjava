package org.clxmm.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Course;
import org.clxmm.service.edu.entity.Teacher;
import org.clxmm.service.edu.entity.form.CourseInfoForm;
import org.clxmm.service.edu.entity.vo.CourseQueryVo;
import org.clxmm.service.edu.entity.vo.CourseVo;
import org.clxmm.service.edu.entity.vo.TeacherQueryVo;
import org.clxmm.service.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/admin/edu/course")
@CrossOrigin
@Api(description = "课程管理")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(value = "课程基本信息",required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        String courseId = courseService.saveCourseInfo(courseInfoForm);

        return R.ok().data("courseId",courseId).message("保存成功");
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("course-info/{id}")
    public R getCourseById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String id
    ) {

        CourseInfoForm courseInfoForm =  courseService.getCourseFormById(id);

        if (courseInfoForm != null) {
            return R.ok().message("获取数据成功").data("courseInfo",courseInfoForm);
        }

        return R.error().message("id不存在");
    }


    @ApiOperation("更新课程")
    @PutMapping("update-course-info")
    public R updateCourseInfo(
            @ApiParam(value = "课程基本信息",required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        String courseId = courseService.updateCourseInfoById(courseInfoForm);

        return R.ok().data("courseId",courseId).message("保存成功");
    }


    @ApiOperation("课程分页查询")
    @GetMapping("/page/{pageNumber}/{pageSize}")
    public R getListPage(
            @ApiParam(value = "当前页" ,defaultValue = "1") @PathVariable Long pageNumber,
            @ApiParam(value = "每页大小",defaultValue = "5") @PathVariable Long pageSize,
            @ApiParam("查询对象") CourseQueryVo courseQueryVo
    ) {


        Page<CourseVo> page = new Page<>(pageNumber,pageSize);
        IPage<CourseVo> page1 = courseService.pageSelect(page, courseQueryVo);
        long total = page1.getTotal();
        List<CourseVo> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);

    }

}

