package org.clxmm.service.ucenter.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.ucenter.entity.vo.RegisterVo;
import org.clxmm.service.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/3 7:41 下午
 */
@Api(description = "会员管理")
@CrossOrigin
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



}
