package org.clxmm.service.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Teacher;
import org.clxmm.service.edu.entity.vo.TeacherQueryVo;
import org.clxmm.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@CrossOrigin
@Api(description = "讲师管理",value = "123")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {



    @Autowired
    private TeacherService teacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/list")
    public R listAll() {
        List<Teacher> list = teacherService.list();
        return  R.ok().data("items",list);
    }

    @ApiOperation(value = "根据id删除讲师",notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("讲师id") @PathVariable("id") String id) {
        boolean b = teacherService.removeById(id);
//        System.out.println("b" + b);

        if (b) {
            return R.ok().message("删除成功");
        }

        return R.error().message("删除失败，没有该讲师");

    }


    @ApiOperation("讲师分页查询")
    @GetMapping("/page/{pageNumber}/{pageSize}")
    public R getListPage(
            @ApiParam(value = "当前页" ,defaultValue = "1") @PathVariable Long pageNumber,
            @ApiParam(value = "每页大小",defaultValue = "5") @PathVariable Long pageSize,
            @ApiParam("查询对象") TeacherQueryVo teacherQueryVo
            ) {

        Page<Teacher> page = new Page<>(pageNumber,pageSize);
        Page<Teacher> page1 = teacherService.pageSelect(page,teacherQueryVo);

        long total = page1.getTotal();
        List<Teacher> records = page.getRecords();

        return R.ok().data("total",total).data("rows",records);

    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public  R save(@ApiParam("讲师对象")  @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }


    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        boolean b = teacherService.updateById(teacher);
        String s  = "数据不存在！" ;
        if (b) {
            s = "修改成功";
            return R.ok().message(s);
        }
        return R.error().message(s);
    }

    @ApiOperation("根据id查看数据")
    @GetMapping("getTeacheById/{id}")
    public R getTeacherById(@ApiParam("id") @PathVariable String id) {

        Teacher teacher = teacherService.getById(id);
        if (teacher == null) {
            return R.error().message("数据不存在");
        }
        return R.ok().data("teacher",teacher);
    }

}

