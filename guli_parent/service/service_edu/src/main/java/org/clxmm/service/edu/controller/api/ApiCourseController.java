package org.clxmm.service.edu.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Course;
import org.clxmm.service.edu.entity.vo.ChapterVo;
import org.clxmm.service.edu.entity.vo.WbeCourseVo;
import org.clxmm.service.edu.entity.vo.WebCourseQueryVo;
import org.clxmm.service.edu.service.ChapterService;
import org.clxmm.service.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/3 3:44 下午
 */
@CrossOrigin
@Api("课程")
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    private ChapterService chapterService;


    @ApiOperation("课程列表")
    @GetMapping("list")
    public R list(@ApiParam(value = "查询对象", required = false)
                          WebCourseQueryVo webCourseQueryVo) {


        List<Course> courseList  = courseService.webSelectList(webCourseQueryVo);
        return R.ok().data("item",courseList).message("课程列表");
    }


    @ApiOperation("根据ID查询课程")
    @GetMapping("get/{courseId}")
    public R getById(
            @ApiParam(value = "课程ID", required = true)
        @PathVariable String courseId){

        //查询课程信息和讲师信息
        WbeCourseVo wbeCourseVo = courseService.selectWebCourseVoById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("course",wbeCourseVo).data("chapterVoList",chapterVoList);

    }



}
