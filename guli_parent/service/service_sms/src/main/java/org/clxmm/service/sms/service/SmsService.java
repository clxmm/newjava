package org.clxmm.service.sms.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/2 7:57 下午
 */
public interface SmsService {
    void send(String mobile, String random) throws ClientException;
}
