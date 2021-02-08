package org.clxmm.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.common.base.result.util.FormUtils;
import org.clxmm.common.base.result.util.MD5;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.ucenter.entity.Member;
import org.clxmm.service.ucenter.entity.vo.RegisterVo;
import org.clxmm.service.ucenter.mapper.MemberMapper;
import org.clxmm.service.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-02-03
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void register(RegisterVo registerVo) {

        // 参数校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if (StringUtils.isBlank(mobile) || !FormUtils.isMobile(mobile)) {
            throw new ClxmmException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        if (StringUtils.isBlank(nickname) || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
            throw new ClxmmException(ResultCodeEnum.PARAM_ERROR);
        }

        // 获取redis 中code
        String redisCode = (String)redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new ClxmmException(ResultCodeEnum.CODE_ERROR);
        }

        // 用户是否被注册
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getMobile,mobile);
        List<Member> memberList = baseMapper.selectList(queryWrapper);
        if (memberList.size()>0) {
            throw new ClxmmException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        // 注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("https://clxmm.oss-cn-beijing.aliyuncs.com/image/%E6%9F%AF%E5%8D%97.jpg");
        member.setDisabled(false);
        baseMapper.insert(member);

    }




}
