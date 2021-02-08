package org.clxmm.service.ucenter.service;

import org.clxmm.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.service.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author clxmm
 * @since 2021-02-03
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);
}