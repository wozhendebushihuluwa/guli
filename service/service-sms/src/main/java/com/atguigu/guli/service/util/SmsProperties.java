package com.atguigu.guli.service.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix="aliyun.sms")
public class SmsProperties {

    private String regionid;
    private String keyid;
    private String keysecret;
    private String templatecode;
    private String signname;
}
