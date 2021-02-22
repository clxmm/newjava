package org.clxmm.service.trade.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.service.base.dto.MemberDto;
import org.clxmm.service.trade.feign.UcenterMemberService;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/13 2:24 下午
 */
@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        log.info("熔断保护");
        return null;
    }
}
