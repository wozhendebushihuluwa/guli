package com.atguigu.guli.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.guli.service.oss.service.FeilService;
import com.atguigu.guli.service.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FeilServiceImpl implements FeilService {

    @Autowired
    private OssProperties ossProperties;




    @Override
    public String upload(InputStream inputStream, String module, String fileName) {

        String endpoint=ossProperties.getEndpoint();
        String accessKeyId=ossProperties.getKeyid();
        String accessKeySecret=ossProperties.getKeysecret();
        String bucketname=ossProperties.getBucketname();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //文件名
        String newFileName= UUID.randomUUID().toString();
        //获得拓展名
        String fileExtention=fileName.substring(fileName.lastIndexOf("."));

        //yourObjectName=bucket下的路径+文件名
        String dataFolder= new DateTime().toString("yyyy/MM/dd");//yyyy/MM/dd
//        String yourObjectName=module+"/"+dataFolder+"/"+newFileName+fileExtention;
        String yourObjectName=new StringBuffer()
                .append(module)
                .append("/")
                .append(dataFolder)
                .append("/")
                .append(newFileName)
                .append(fileExtention)
                .toString();
        ossClient.putObject(bucketname, yourObjectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        return new StringBuffer().append("https://")
                .append(bucketname)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(yourObjectName)
                .toString();
    }

    @Override
    public void removeFile(String url) {

        String endpoint=ossProperties.getEndpoint();
        String accessKeyId=ossProperties.getKeyid();
        String accessKeySecret=ossProperties.getKeysecret();
        String bucketname=ossProperties.getBucketname();
        // 指定前缀。
        String host="https://"+bucketname+"."+endpoint+"/";
        String objectName = url.substring(host.length());
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketname, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    }

