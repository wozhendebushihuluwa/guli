package com.atguigu.guli.service.edu.client;

import com.atguigu.guli.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient("guli-oss")
public interface OssClient {

    @DeleteMapping(value = "/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}
