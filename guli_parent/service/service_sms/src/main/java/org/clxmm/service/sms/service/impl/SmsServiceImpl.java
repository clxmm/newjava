package org.clxmm.service.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.sms.service.SmsService;
import org.clxmm.service.sms.util.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/2 7:57 下午
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void send(String mobile, String random) throws ClientException {

        // 创建配置对象
        DefaultProfile profile = DefaultProfile.getProfile(
                smsProperties.getRegionId(),
                smsProperties.getKeyId(),
                smsProperties.getKeySecret());

        // 创建并组装参数
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", smsProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", smsProperties.getSignName());
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());
        Map<String,String> map = new HashMap<>();
        map.put("code",random);
        Gson gson = new Gson();
        String tempCode = gson.toJson(map);
        request.putQueryParameter("TemplateParam", tempCode);
        CommonResponse response = client.getCommonResponse(request);
        String data = response.getData();
        //解析json字符串格式的响应结果
        HashMap<String,String> resultMap = gson.fromJson(data, HashMap.class);

        String code = resultMap.get("Code");
        String message = resultMap.get("message");

        if (!"OK".equals(code)) {
            log.error("短信发送失败 " + " - code: " + code + ", message: " + message);
            throw new ClxmmException(ResultCodeEnum.SMS_SEND_ERROR);
        }



        System.out.println(data);
        // //https://help.aliyun.com/document_detail/101346.html?spm=a2c4g.11186623.6.613.3f6e2246sDg6Ry
        if ("isv.BUSINESS_LIMIT_CONTROL".equals(random)) {
            log.error("短信发送过于频繁 " + "【code】" + random + ", 【message】" + message);
            throw new ClxmmException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }

    }
}
