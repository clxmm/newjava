package org.clxmm.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoInfosRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.vod.service.VideoService;
import org.clxmm.service.vod.util.AliyunVodSDKUtils;
import org.clxmm.service.vod.util.VodProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/26 8:50 下午
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VodProperties vodProperties;

    @Override
    public String uploadVideo(InputStream file, String originalFilename) {

        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyid(),
                vodProperties.getKeysecret(), title, originalFilename, file);

        /* 模板组ID(可选) */
//        request.setTemplateGroupId(vodProperties.getTemplateGroupId());
        /* 工作流ID(可选) */
//        request.setWorkflowId(vodProperties.getWorkflowId());
        UploadVideoImpl uploader = new UploadVideoImpl();

        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();

        if (StringUtils.isEmpty(videoId)) {
            log.error("阿里云上传失败：" + response.getCode() + " - " + response.getMessage());
            throw new ClxmmException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
        return videoId;
    }

    @Override
    public void removeVideo(String videoId) throws ClientException {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyid(), vodProperties.getKeysecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }

    @Override
    public void removeVideoByList(List<String> ids) throws ClientException {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyid(), vodProperties.getKeysecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        StringBuffer idListStr = new StringBuffer();
        int size = ids.size();
        for (int i = 0; i < size; i++) {
            idListStr.append(ids.get(i));
            if (i == size - 1 || i % 20 == 19) {
                System.out.println("idListStr = " + idListStr.toString());
                request.setVideoIds(idListStr.toString());
                client.getAcsResponse(request);
            } else if (i % 20 < 19) {
                idListStr.append(",");
            }


        }


    }

    @Override
    public String getPlayAuth(String videoSourceId) throws ClientException {
        //初始化client对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyid(), vodProperties.getKeysecret());

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoSourceId);

        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        return response.getPlayAuth();
    }


}
