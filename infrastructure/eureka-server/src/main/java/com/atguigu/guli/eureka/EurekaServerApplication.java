package com.atguigu.guli.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author helen
 * @since 2020/3/2
 */
@SpringBootApplication
@EnableEurekaServer //当前微服务是注册中心服务器：必选项
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class);
    }
}