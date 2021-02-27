package org.clxmm.service.edu.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Video;
import org.clxmm.service.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/admin/edu/video")
//@CrossOrigin
@Api(description = "A课时管理")
@Slf4j
public class VideoController {


    @Autowired
    private VideoService videoService;


    @ApiOperation("新增课时")
    @PostMapping("save")
    public R save(
            @ApiParam(value = "课时对象",required = true)
            @RequestBody Video video
            ) {

        boolean save = videoService.save(video);
        if (save) {
            return R.ok().message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("修改课时")
    @PutMapping("update")
    public R update(
            @ApiParam(value = "课时对象",required = true)
            @RequestBody Video video
    ) {
        boolean update = videoService.updateById(video);
        if (update) {
            return  R.ok().message("修改成功");
        }
        return R.error().message("修改课时失败");
    }

    @ApiOperation("根据id查询课时")
    @GetMapping("getVideoById/{id}")
    public R getVideoById(
            @ApiParam(value = "课时id",required = true)
            @PathVariable String id
    ) {
        Video video = videoService.getById(id);
        if (video!=null) {
            return R.ok().data("item",video);
        }
        return R.error().message("课时不存在");
    }

    @ApiOperation("根据id删除课时")
    @DeleteMapping("deleteVideoById/{id}")
    public R deleteVideoById(
            @ApiParam(value = "课时id",required = true)
            @PathVariable String id
    ) {

        // 删除阿里云上的视频
        videoService.removeMediaVideoById(id);

        boolean remove = videoService.removeById(id);
        if (remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }


}

