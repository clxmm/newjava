package org.clxmm.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.clxmm.service.oss.service.FileService;
import org.clxmm.service.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/16 9:35 下午
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    public static void main(String[] args) {
        System.out.println(new DateTime().toString("yyyyMMddHHmmss"));
    }

    @Override
    public String upload(InputStream inputStream, String model, String originalFilename) {

        // 配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);

        if (!ossClient.doesBucketExist(bucketname)) {
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
            ossClient.createBucket(bucketname);
        }

        // 构建objectName： 文件路径 image/柯南.jpg          image/2020/04/15/xx.jpg


        String folder = new DateTime().toString("yyyyMMdd");
        String fileName = new DateTime().toString("yyyyMMddHHmmss");
        String fileExtendName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = model + "/" + folder + "/" + fileName + fileExtendName;


//        创建PutObjectRequest对象。
        ossClient.putObject(ossProperties.getBucketname(), key, inputStream);

        ossClient.shutdown();
        return "https://" + bucketname + "." + endpoint + "/" + key;
    }


    @Override
    public void removeFile(String url) {
        // 配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        // https://clxmm.oss-cn-beijing.aliyuncs.com/
        String host = "https://" + bucketname + "."+endpoint +"/";
        String objectName = url.substring(host.length());
        ossClient.deleteObject(bucketname,objectName);
        ossClient.shutdown();
    }
}
