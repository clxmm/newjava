package org.clxmm.service.edu.feifn;

import io.swagger.annotations.ApiParam;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.feifn.fallback.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/21 9:01 下午
 */
@FeignClient(value = "service-oss",fallback = OssFileServiceFallBack.class)
public interface OssFileService {


    @GetMapping("/admin/oss/file/test")
    R test();

    /**
     * 删除文件
     * @return
     */
    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(String url);


}
