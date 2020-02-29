package com.atguigu.guli.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;

public interface VideoService {

    String uploadVideo(InputStream inputStream,String originalFilename);

    void removeVideo(String videoId) throws ClientException;
}
