package com.atguigu.guli.service.service;

import java.util.Map;

public interface SmsService {

    void send(String phone, Map<String,Object> param);
}
