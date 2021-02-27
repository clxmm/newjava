package org.clxmm.service.edu.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.ExceptionUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.edu.entity.vo.SubjectVo;
import org.clxmm.service.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/admin/edu/subject")
//@CrossOrigin
@Api(description = "课程分类管理")
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("excel 导入课程分类")
    @PostMapping("import")
    public R batchImport(@RequestParam("file") MultipartFile file) {

        try {
            subjectService.batchImport(file.getInputStream());
            return R.ok().message("导入成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
//            e.printStackTrace();
            throw new ClxmmException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation("嵌套数据列表")
    @PostMapping("nested-list")
    public R nestedList() {
        List<SubjectVo> subjectVoList = subjectService.nestedList();
        return R.ok().data("items",subjectVoList);
    }


}

