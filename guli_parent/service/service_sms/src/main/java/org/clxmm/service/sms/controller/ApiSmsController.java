package org.clxmm.service.sms.controller;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/2 7:56 下午
 */

import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.common.base.result.util.FormUtils;
import org.clxmm.common.base.result.util.RandomUtils;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/sms")
@Api(description = "短信管理")
@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("send/{mobile}")
    public R getCode(@PathVariable String mobile) throws  ClientException {


        //校验手机号是否合法
        if (StringUtils.isBlank(mobile) || !FormUtils.isMobile(mobile)) {
            log.error("请输入正确的手机号码 {}", mobile);
            throw new ClxmmException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }


        String random = RandomUtils.getSixBitRandom();

        // snd msg
//        smsService.send(mobile,random);

        // save code    5 minutes
        redisTemplate.opsForValue().set(mobile,random,5 , TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }


}
