package org.clxmm.service.ucenter.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.clxmm.common.base.result.R;
import org.clxmm.service.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/27 9:02 下午
 */
//@CrossOrigin
@Api(description = "会员管理")
@RestController
@RequestMapping("/admin/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 2021-01-01
    @ApiOperation(value = "根据日期统计注册人数")
    @GetMapping(value = "count-register-num/{day}")
    public R countRegisterNum(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day) {
        Integer num = memberService.countRegisterNum(day);
        return R.ok().data("registerNum", num);
    }

}
