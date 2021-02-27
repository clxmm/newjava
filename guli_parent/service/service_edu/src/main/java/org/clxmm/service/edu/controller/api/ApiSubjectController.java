package org.clxmm.service.edu.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/4 8:21 下午
 */
//@CrossOrigin
@Api("课程分类")
@RestController
@RequestMapping("/api/edu/subject")
public class ApiSubjectController {

    @Autowired
    private SubjectService subjectService;


    @ApiOperation("课程分类")
    @GetMapping("list")
    public R list () {
        return R.ok().data("item",subjectService.nestedList());
    }

}
