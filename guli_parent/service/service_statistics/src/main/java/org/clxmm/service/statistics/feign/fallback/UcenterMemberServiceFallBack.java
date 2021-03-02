package org.clxmm.service.statistics.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.statistics.feign.UcenterMemberService;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/27 9:32 下午
 */
@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {

    @Override
    public R countRegisterNum(String day) {
//错误日志
        log.error("熔断器被执行");
        return R.ok().data("registerNum", 0);
    }
}
