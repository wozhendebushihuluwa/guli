package com.atguigu.guli.service.controller.api;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.util.RandomUtils;
import com.atguigu.guli.service.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Api(description = "短信服务")
@CrossOrigin
@Slf4j
@RequestMapping("api/sms")
public class ApiSmsController {

    @Autowired
    private SmsService smsService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public R getCode(
            @ApiParam(name = "phone",value = "电话号码",required = true)
            @PathVariable String phone
    ){
        //生成验证码
        String code = RandomUtils.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);

        //发送短信验证码
//        smsService.send(phone, param);

        //将验证码存入redis缓存
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }
}
