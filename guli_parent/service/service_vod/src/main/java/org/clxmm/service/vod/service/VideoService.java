package org.clxmm.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/26 8:49 下午
 */
public interface VideoService {

    String uploadVideo(InputStream file, String originalFilename);


    void removeVideo(String videoId) throws ClientException;

    void removeVideoByList(List<String> ids) throws ClientException;

}
