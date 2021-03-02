package org.clxmm.service.sms.controller;

import org.clxmm.common.base.result.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/1 8:54 下午
 */
@RestController
@RequestMapping("/sms/sample")
@RefreshScope  // 启动动态刷新
public class SampleController {

    @Value("${aliyun.sms.signName}")
    private String signName;

    @GetMapping("test1")
    public R test1() {
        return R.ok().data("signName", signName);
    }

}
