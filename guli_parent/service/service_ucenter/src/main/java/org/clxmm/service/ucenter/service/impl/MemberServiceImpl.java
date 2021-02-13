package org.clxmm.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.common.base.result.util.FormUtils;
import org.clxmm.common.base.result.util.JwtInfo;
import org.clxmm.common.base.result.util.JwtUtils;
import org.clxmm.common.base.result.util.MD5;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.ucenter.entity.Member;
import org.clxmm.service.ucenter.entity.vo.LoginVo;
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
        String redisCode = (String) redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new ClxmmException(ResultCodeEnum.CODE_ERROR);
        }

        // 用户是否被注册
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getMobile, mobile);
        List<Member> memberList = baseMapper.selectList(queryWrapper);
        if (memberList.size() > 0) {
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

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        // 校验
        if (StringUtils.isBlank(mobile) || !FormUtils.isMobile(mobile) || StringUtils.isBlank(password)) {
            throw new ClxmmException(ResultCodeEnum.PARAM_ERROR);
        }

        LambdaQueryWrapper<Member> memberLambdaQueryWrapper = new LambdaQueryWrapper<>();

        memberLambdaQueryWrapper.eq(Member::getMobile, mobile);

        List<Member> members = baseMapper.selectList(memberLambdaQueryWrapper);


        if (members == null || members.size() == 0) {
            throw new ClxmmException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        Member member = members.get(0);

        //密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new ClxmmException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        if (member.getDisabled()) {
            throw new ClxmmException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }


        //登陆,生成token

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());

        String token = JwtUtils.getJwtToken(jwtInfo, 30 * 60);

        return token;
    }

    @Override
    public Member getByOpenid(String openid) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getOpenid, openid);
        return baseMapper.selectOne(queryWrapper);
    }


}
