package org.clxmm.service.ucenter.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.common.base.result.util.JwtInfo;
import org.clxmm.common.base.result.util.JwtUtils;
import org.clxmm.service.base.dto.MemberDto;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.ucenter.entity.vo.LoginVo;
import org.clxmm.service.ucenter.entity.vo.RegisterVo;
import org.clxmm.service.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/3 7:41 下午
 */
@Api(description = "会员管理")
//@CrossOrigin
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
public class ApiMemberController {

    @Autowired
    private MemberService memberService;


    @ApiOperation("会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {

        memberService.register(registerVo);

        return R.ok().message("注册成功");
    }

    @ApiOperation(value = "会员登陆")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String toke = memberService.login(loginVo);

        return R.ok().data("token", toke);

    }


    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("get-login-info")
    public R getLoginInfo(HttpServletRequest request) {
        try {
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return R.ok().data("userInfo", jwtInfo);
        } catch (Exception e) {
            log.error("解析用户信息失败，" + e.getMessage());
            throw new ClxmmException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }


    @ApiOperation("根据会员id查询会员信息")
    @GetMapping("inner/get-member-dto/{memberId}")
    public MemberDto getMemberDtoByMemberId(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable String memberId) {
        MemberDto memberDto = memberService.getMemberDtoByMemberId(memberId);
        return memberDto;
    }


}
