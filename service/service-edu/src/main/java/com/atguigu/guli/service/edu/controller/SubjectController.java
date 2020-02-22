package com.atguigu.guli.service.edu.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.common.base.util.ExceptionUtils;
import com.atguigu.guli.service.base.exception.GuliException;
import com.atguigu.guli.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
@RestController
@RequestMapping("/admin/edu/subject")
@CrossOrigin
@Api(description = "课程类别信息")
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "Excel导入课程类别课程")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(name = "file",value = "Excel文件",required = true)
            @RequestParam("file")MultipartFile file
            ){
        try {
            InputStream inputStream = file.getInputStream();
            this.subjectService.batchImport(inputStream);
            return R.ok().message("批量导入成功");
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }
}

