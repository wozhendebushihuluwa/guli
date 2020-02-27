package com.atguigu.guli.service.vod.service;

import java.io.InputStream;

public interface VideoService {

    String uploadVideo(InputStream inputStream,String originalFilename);
}
