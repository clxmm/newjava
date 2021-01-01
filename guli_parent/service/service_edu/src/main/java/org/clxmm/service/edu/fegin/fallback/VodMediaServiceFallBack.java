package org.clxmm.service.edu.fegin.fallback;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.fegin.VodMediaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/28 8:10 下午
 */
@Slf4j
@Service
public class VodMediaServiceFallBack  implements VodMediaService {

    @Override
    public R removeVideo(String vodId) {
        log.info("熔断保护");
        return R.error();
    }

    @Override
    public R removeVideoByIdList(List<String> videoIdList) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
