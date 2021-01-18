package org.clxmm.service.cms.fegin.fallback;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.cms.fegin.OssFileService;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/10 3:15 下午
 */
@Service
@Slf4j
public class OssFileServiceFallBack  implements OssFileService {
    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
