package org.clxmm.service.oss.service;

import java.io.InputStream;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/16 9:32 下午
 */
public interface FileService {

    /**
     *      文件上传
     * @param inputStream 文档流
     * @param model 问价夹名称
     * @param originalFilename  原始文件名字
     * @return 读取文件的url地址
     */
    String upload(InputStream inputStream,String model,String originalFilename);

    /**
     * 删除文件
     * @param url
     */
    void removeFile(String url);

}
