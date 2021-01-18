package org.clxmm.service.cms.fegin;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/10 3:15 下午
 */

import org.clxmm.common.base.result.R;
import org.clxmm.service.cms.fegin.fallback.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@FeignClient(value = "service-oss", fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}
