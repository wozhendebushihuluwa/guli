package com.atguigu.guli.service.base.exception;

import com.atguigu.guli.common.base.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "全局异常对象")
public class GuliException extends RuntimeException {

    private Integer code;

    public GuliException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }
}
