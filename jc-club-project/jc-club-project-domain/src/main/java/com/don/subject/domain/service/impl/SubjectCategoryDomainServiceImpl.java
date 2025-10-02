package com.don.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.domain.convert.SubjectCategoryConverter;
import com.don.subject.domain.entity.SubjectCategoryBO;
import com.don.subject.domain.entity.SubjectLabelBO;
import com.don.subject.domain.service.SubjectCategoryDomainService;
import com.don.subject.infra.basic.entity.SubjectCategory;
import com.don.subject.infra.basic.entity.SubjectLabel;
import com.don.subject.infra.basic.entity.SubjectMapping;
import com.don.subject.infra.basic.service.SubjectCategoryService;
//import jakarta.annotation.Resource;
import com.don.subject.infra.basic.service.SubjectLabelService;
import com.don.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

//import javax.annotation.Resource;

/**
 * @author A
 * @date 2025/8/12
 **/

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Autowired
    private SubjectCategoryService subjectCategoryService;

    @Autowired
    private SubjectMappingService subjectMappingService;

    @Autowired
    private SubjectLabelService subjectLabelService;

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
            log.info("SubjectCategoryDomainService.queryCategory.subjectCategoryBOList:{}",JSON.toJSONString(subjectCategoryBOList));
        }
        for (SubjectCategoryBO categoryBO : subjectCategoryBOList) {
            Integer subjectCount = subjectCategoryService.querySubjectCount(categoryBO.getId());
            categoryBO.setCount(subjectCount);
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

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainService.queryCategoryAndLabel.entityList:{}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryList);
        for (SubjectCategoryBO categoryBO : categoryBOList) {
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setCategoryId(categoryBO.getId());
            List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
            if (CollectionUtils.isEmpty(subjectMappingList))
                continue;

            List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).toList();
            List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
            List<SubjectLabelBO> labelBOList = new LinkedList<>();
            for (SubjectLabel subjectLabel : subjectLabelList) {
                SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
                subjectLabelBO.setId(subjectLabel.getId());
                subjectLabelBO.setLabelName(subjectLabel.getLabelName());
                subjectLabelBO.setCategoryId(subjectLabel.getCategoryId());
                subjectLabelBO.setSortNum(subjectLabel.getSortNum());
                labelBOList.add(subjectLabelBO);
            }
            categoryBO.setLabelBOList(labelBOList);
        }
        return categoryBOList;
    }
}
