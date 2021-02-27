package org.clxmm.service.edu.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Teacher;
import org.clxmm.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/1 7:40 下午
 */
//@CrossOrigin
@Api("讲师")
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {


    @Autowired
    private TeacherService teacherService;

    @ApiOperation("讲师列表")
    @GetMapping("list")
    public R ListAll() {
        List<Teacher> list = teacherService.list();
        return R.ok().data("item",list).message("获取讲师列表成功");
    }

    @ApiOperation("根据讲师id获取讲师信息")
    @GetMapping("getTeachInfo/{id}")
    public R getTeacherInfoById(
            @ApiParam("讲师id")
            @PathVariable String id
    ) {
        Map<String,Object> map = teacherService.getTeacherInfoById(id);

        return R.ok().data("item",map);
    }




}
