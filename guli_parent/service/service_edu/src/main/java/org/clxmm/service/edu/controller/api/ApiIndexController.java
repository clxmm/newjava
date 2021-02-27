package org.clxmm.service.edu.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Course;
import org.clxmm.service.edu.entity.Teacher;
import org.clxmm.service.edu.service.CourseService;
import org.clxmm.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/30 10:04 下午
 */
//@CrossOrigin
@Api(description="首页")
@RestController
@RequestMapping("/api/edu/index")
public class ApiIndexController {


    @Autowired
    private CourseService courseService;


    @Autowired
    private TeacherService teacherService;



    @ApiOperation("课程列表")
    @GetMapping
    public R index(){

        //获取首页最热门前8条课程数据
        List<Course> courseList = courseService.selectHotCourse();
        //获取首页推荐前4条讲师数据
        List<Teacher> teacherList = teacherService.selectHotTeacher();
        return R.ok().data("courseList", courseList).data("teacherList", teacherList);
    }





}
