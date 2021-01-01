package org.clxmm.service.edu.controller.admin;


import com.sun.xml.bind.v2.model.core.ID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Chapter;
import org.clxmm.service.edu.entity.vo.ChapterVo;
import org.clxmm.service.edu.service.ChapterService;
import org.clxmm.service.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 章节管理
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/admin/edu/chapter")
@CrossOrigin
@Api(description = "章节管理")
@Slf4j
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;


    @ApiOperation("新增章节")
    @PostMapping("save")
    public R save(
            @ApiParam(value = "章节对象", required = true)
            @RequestBody Chapter chapter
    ) {

        boolean save = chapterService.save(chapter);
        if (save) {
            return R.ok().message("新增章节成功");
        }
        return R.error().message("章节数据保存失败");
    }


    @ApiOperation("根据id查询章节")
    @GetMapping("gatChapterById/{id}")
    public R getChapterById(
            @ApiParam(value = "章节id", required = true)
            @PathVariable String id
    ) {
        Chapter chapter = chapterService.getById(id);
        if (chapter != null) {
            return R.ok().data("item",chapter);
        }
        return R.error().message("章节数据不存在");
    }


    @ApiOperation("根据id修改章节")
    @PutMapping("updateChapterById")
    public R updateChapterById(
            @ApiParam(value = "章节对象", required = true)
            @RequestBody Chapter chapter
    ) {
        boolean update = chapterService.updateById(chapter);
        if (update) {
            return R.ok().message("更新章节成功");
        }
        return R.error().message("章节数据不存在");
    }


    @ApiOperation("根据ID删除章节")
    @DeleteMapping("deleteChapterById/{id}")
    public R deleteChapterById(
            @ApiParam(value = "章节id", required = true)
            @PathVariable String id
    ) {

        // todo 删除视频：VOD
        videoService.removeMediaVideoByChapterId(id);

        boolean result = chapterService.deleteChapterById(id);

        if (result) {
            return R.ok().message("删除章节成功");
        }


        return R.error().message("数据不存在");
    }


    @ApiOperation("嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(value = "课程id",required = true)
            @PathVariable String courseId
    ) {
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("item",chapterVoList);
    }

}

