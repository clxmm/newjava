package org.clxmm.service.edu.controller;

import org.clxmm.common.base.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/11 9:52 下午
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("login")
    public R login() {

        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info() {

        return R.ok()

                .data("roles","[admin]")

                .data("name","admin")

                .data("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    /**
     29
     * 退出
     30
     * @return
    31
     */

    @PostMapping("logout")

    public R logout(){

        return R.ok();

    }


}
