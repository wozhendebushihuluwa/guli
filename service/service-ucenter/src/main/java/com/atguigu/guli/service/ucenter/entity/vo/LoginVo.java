package com.atguigu.guli.service.ucenter.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}
