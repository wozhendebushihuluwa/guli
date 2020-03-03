package com.atguigu.guli.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.Map;

public interface VideoService {

    String uploadVideo(InputStream inputStream,String originalFilename);

    void removeVideo(String videoId) throws ClientException;

    Map<String,Object> getVideoUploadAuthAndAddress(String title, String fileName) throws ClientException;

    Map<String, Object> refreshVideoUploadAuth(String videoId) throws ClientException;

    String getVideoPlayAuth(String videoId) throws ClientException;
}
