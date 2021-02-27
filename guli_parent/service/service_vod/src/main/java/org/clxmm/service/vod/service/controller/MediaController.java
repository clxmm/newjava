package org.clxmm.service.vod.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.ExceptionUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/27 3:00 下午
 */
@Api("阿里云视频点播")
//@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/admin/vod/media")
public class MediaController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile file
    ) {

        try {
            InputStream inputStream = file.getInputStream();
            String videoId = videoService.uploadVideo(inputStream, file.getOriginalFilename());
            return R.ok().message("视频上传·1成功").data("videoId", videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ClxmmException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }

    @DeleteMapping("remove/{vodId}")
    public R removeVideo(
            @ApiParam(value = "阿里云视频id", required = true)
            @PathVariable String vodId) {
        try {
            videoService.removeVideo(vodId);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ClxmmException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }

    }

    @DeleteMapping("remove")
    public R removeVideoByIdList(
            @ApiParam(value = "阿里云视频id列表", required = true)
            @RequestBody List<String> ids
            ) {
        try {
            videoService.removeVideoByList(ids);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ClxmmException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }

    }

}
