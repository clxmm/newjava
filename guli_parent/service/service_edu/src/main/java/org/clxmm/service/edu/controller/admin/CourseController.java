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
import org.clxmm.service.edu.entity.vo.CoursePublishVo;
import org.clxmm.service.edu.entity.vo.CourseQueryVo;
import org.clxmm.service.edu.entity.vo.CourseVo;
import org.clxmm.service.edu.entity.vo.TeacherQueryVo;
import org.clxmm.service.edu.service.CourseService;
import org.clxmm.service.edu.service.VideoService;
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

    @Autowired
    private VideoService videoService;

    @ApiOperation("新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        String courseId = courseService.saveCourseInfo(courseInfoForm);

        return R.ok().data("courseId", courseId).message("保存成功");
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("course-info/{id}")
    public R getCourseById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String id
    ) {

        CourseInfoForm courseInfoForm = courseService.getCourseFormById(id);

        if (courseInfoForm != null) {
            return R.ok().message("获取数据成功").data("courseInfo", courseInfoForm);
        }

        return R.error().message("id不存在");
    }


    @ApiOperation("更新课程")
    @PutMapping("update-course-info")
    public R updateCourseInfo(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        String courseId = courseService.updateCourseInfoById(courseInfoForm);

        return R.ok().data("courseId", courseId).message("保存成功");
    }


    @ApiOperation("课程分页查询")
    @GetMapping("/page/{pageNumber}/{pageSize}")
    public R getListPage(
            @ApiParam(value = "当前页", defaultValue = "1") @PathVariable Long pageNumber,
            @ApiParam(value = "每页大小", defaultValue = "5") @PathVariable Long pageSize,
            @ApiParam("查询对象") CourseQueryVo courseQueryVo
    ) {


        Page<CourseVo> page = new Page<>(pageNumber, pageSize);
        IPage<CourseVo> page1 = courseService.pageSelect(page, courseQueryVo);
        long total = page1.getTotal();
        List<CourseVo> records = page.getRecords();
        return R.ok().data("total", total).data("rows", records);

    }


    @ApiOperation(value = "根据id删除课程", notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("课程id") @PathVariable("id") String id) {

        // 删除课程里面的视频
        videoService.removeMediaVideoByCourseId(id);

        //删除课程封面
        courseService.removeCoverById(id);
        // 删除课程
        boolean b = courseService.removeCourseById(id);

        if (b) {
            return R.ok().message("删除成功");
        }

        return R.error().message("删除失败，没有该讲师");

    }


    @ApiOperation("根据ID获取课程发布信息")
    @GetMapping("course-publish/{id}")
    public R getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id

    ) {

        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);

        if (coursePublishVo != null) {
            return R.ok().data("item", coursePublishVo);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id) {

        Boolean result = courseService.publishCourseById(id);

        if (result) {
            return  R.ok().message("发布课程成功");
        }

        return R.error().message("数据不存在");

    }
}

