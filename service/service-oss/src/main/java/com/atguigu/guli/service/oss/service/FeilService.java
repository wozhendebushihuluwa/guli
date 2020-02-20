package com.atguigu.guli.service.oss.service;

import java.io.InputStream;

public interface FeilService {
    String upload(InputStream inputStream,String module,String fileName);

    void removeFile(String url);
}
