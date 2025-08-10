package com.don.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.domain.convert.SubjectCategoryConverter;
import com.don.subject.domain.entity.SubjectCategoryBO;
import com.don.subject.domain.service.SubjectCategoryDomainService;
import com.don.subject.infra.basic.entity.SubjectCategory;
import com.don.subject.infra.basic.service.SubjectCategoryService;
//import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import javax.annotation.Resource;

/**
 * @author A
 * @date 2025/8/12
 **/

@Service
@Slf4j
public class SubjectCategoryDomainServiceIml implements SubjectCategoryDomainService {

    @Autowired
    private SubjectCategoryService subjectCategoryService;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainService.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.
                convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryPrimaryCategoryList(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryList);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryService.queryCategoryByPrimary:{}",JSON.toJSONString(subjectCategoryBOList));
        }
        return subjectCategoryBOList;
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryList);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainService.queryCategory:{}",JSON.toJSONString(subjectCategoryBOList));
        }
        return subjectCategoryBOList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        int cnt = subjectCategoryService.update(subjectCategory);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainService.update:{}",JSON.toJSONString(subjectCategory));
        }
        return cnt > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int cnt = subjectCategoryService.update(subjectCategory);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainService.delete:{}",JSON.toJSONString(subjectCategory));
        }
        return cnt > 0;
    }
}
