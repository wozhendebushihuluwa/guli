package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.common.base.util.ExcelImportUtil;
import com.atguigu.guli.service.edu.entity.Subject;
import com.atguigu.guli.service.edu.entity.vo.SubjectVo;
import com.atguigu.guli.service.edu.mapper.SubjectMapper;
import com.atguigu.guli.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchImport(InputStream inputStream) throws Exception {

        ExcelImportUtil excelImportUtil = new ExcelImportUtil(inputStream);
        HSSFSheet sheet = excelImportUtil.getSheet();
        for (Row rowData : sheet) {
            //获取当前行的索引值：标题行
            if(rowData.getRowNum()==0){
                continue; //忽略当前行
            }
            //获取数据行
            Cell levelOneCell = rowData.getCell(0);//获得一级分类
            String levelOneCellValue = excelImportUtil.getCellValue(levelOneCell).trim();
            if(levelOneCell==null|| StringUtils.isEmpty(levelOneCellValue)){
                continue;
            }

            Cell levelTwoCell = rowData.getCell(1);//获得二级分类
            String levelTwoCellValue = excelImportUtil.getCellValue(levelTwoCell).trim();
            if(levelTwoCell==null|| StringUtils.isEmpty(levelTwoCellValue)){
                continue;
            }

            //判断一级分类是否重复
            String parentId=null;
            Subject subject = this.getByTitle(levelOneCellValue);
            if(subject==null){
                //将一级分类存入数据库
                Subject subjectLevelOne = new Subject();
                subjectLevelOne.setTitle(levelOneCellValue);
                baseMapper.insert(subjectLevelOne);

                parentId = subjectLevelOne.getId();
            }else {
                parentId = subject.getId();
            }

            //判断二级分类是否重复
            Subject subSubject = this.getSubByTitle(levelTwoCellValue, parentId);
            if(subSubject==null){
                //将二级分类存入数据库
                Subject subjectLevelTwo = new Subject();
                subjectLevelTwo.setTitle(levelTwoCellValue);
                subjectLevelTwo.setParentId(parentId);
                baseMapper.insert(subjectLevelTwo);
            }
        }
    }

    @Override
    public List<SubjectVo> nestedList() {

        List<SubjectVo> subjectVoList = new ArrayList<>();
        //获取所有分类的数据
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<Subject>();
        queryWrapper.orderByAsc("sort","id");
        List<Subject> subjectList = baseMapper.selectList(queryWrapper);

        //分别获取一级分类和二级分类
        ArrayList<Subject> subjectLevelOneList = new ArrayList<>();
        ArrayList<Subject> subjectLevelTwoList = new ArrayList<>();
        for (Subject subject : subjectList) {
            if(subject.getParentId().equals("0")){
                subjectLevelOneList.add(subject);//填充一级类别
            }else {
                subjectLevelTwoList.add(subject);//填充二级类别
            }
        }
        //填充vo数据：一级类别
        for (Subject subjectLevelOne : subjectLevelOneList) {
            SubjectVo subjectVoLevelOne = new SubjectVo();
            BeanUtils.copyProperties(subjectLevelOne,subjectVoLevelOne);
            subjectVoList.add(subjectVoLevelOne);

            //填充children:二级类别
            List<SubjectVo> subjectVoLevelTwoList = new ArrayList<>();
            for (Subject subjectLevelTwo : subjectLevelTwoList) {
                if(subjectLevelOne.getId().equals(subjectLevelTwo.getParentId())){
                    SubjectVo subjectVoLevelTwo = new SubjectVo();
                    BeanUtils.copyProperties(subjectLevelTwo,subjectVoLevelTwo);
                    subjectVoLevelTwoList.add(subjectVoLevelTwo);
                }
            }
            subjectVoLevelOne.setChildren(subjectVoLevelTwoList);
        }
        return subjectVoList;
    }

    @Override
    public List<SubjectVo> nestedList2() {

        return baseMapper.selectNestedByParentId("0");
    }

    //判断一级分类是否重复
    private Subject getByTitle(String title){
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",title);
        subjectQueryWrapper.eq("parent_id","0");

        return baseMapper.selectOne(subjectQueryWrapper);
    }

    //判断二级分类是否重复
    private Subject getSubByTitle(String title,String parenId){
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",title);
        subjectQueryWrapper.eq("parent_id",parenId);

        return baseMapper.selectOne(subjectQueryWrapper);
    }
}
