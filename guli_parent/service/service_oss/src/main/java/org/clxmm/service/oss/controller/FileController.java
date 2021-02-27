package org.clxmm.service.oss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.ExceptionUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/17 8:56 下午
 */
@Api(description = "阿里云文件管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {


    @Autowired
    private FileService fileService;


    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module) throws IOException {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String url = fileService.upload(inputStream, module, originalFilename);
            return R.ok().message("文件上传成功").data("url", url);

        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ClxmmException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }


    @ApiOperation(value = "文件删除")
    @DeleteMapping("remove")
    public R removeFile(
            @ApiParam(value = "url路径", required = true)
            @RequestBody String url) {
        fileService.removeFile(url);
        log.info("删除文件成功" + url);
        return R.ok().message("文件删除成功!");
    }


    @ApiOperation(value = "测试")
    @GetMapping("test")
    public R test() {
        log.info("远程方法被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {

        }

        log.info("远程方法被调用");
        return R.ok().data("test", "test");
    }




}
