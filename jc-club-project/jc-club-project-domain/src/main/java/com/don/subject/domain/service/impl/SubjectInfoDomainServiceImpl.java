package com.don.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.don.subject.common.entity.PageResult;
import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.domain.convert.SubjectInfoConverter;
import com.don.subject.domain.convert.SubjectLabelConverter;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectLabelBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import com.don.subject.domain.handler.subject.SubjectTypeHandler;
import com.don.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.don.subject.domain.service.SubjectInfoDomainService;
import com.don.subject.domain.service.SubjectLabelDomainService;
import com.don.subject.infra.basic.entity.SubjectInfo;
import com.don.subject.infra.basic.entity.SubjectLabel;
import com.don.subject.infra.basic.entity.SubjectMapping;
import com.don.subject.infra.basic.service.SubjectInfoService;
import com.don.subject.infra.basic.service.SubjectLabelService;
import com.don.subject.infra.basic.service.SubjectMappingService;
import com.don.subject.infra.basic.service.impl.SubjectLabelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author A
 * @date 2025/8/15
 **/

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Autowired
    private SubjectInfoService subjectInfoService;

    @Autowired
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Autowired
    private SubjectMappingService subjectMappingService;

    @Autowired
    private SubjectLabelDomainService subjectLabelDomainService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainService.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }

        subjectInfoBO.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfoService.insert(subjectInfo);
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        subjectTypeHandler.add(subjectInfoBO);
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> subjectInfoBOLabelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new ArrayList<>();
        for (Long categoryId : categoryIds)
            for (Long labelId : subjectInfoBOLabelIds) {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
                mappingList.add(subjectMapping);
            }
        subjectMappingService.insertBatch(mappingList);
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId());
        if(count == 0)
            return pageResult;
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize() + 1;
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertToBoInfo(subjectInfoList);
        pageResult.setRecords(subjectInfoBOList);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBOParam) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBOParam.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBO subjectInfoBO = SubjectInfoConverter.INSTANCE.convertOpAndInfoToBo(optionBO, subjectInfo);
        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
        subjectLabelBO.setCategoryId(subjectInfoBO.getCategoryId());
        List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
        List<String> labelNameList = subjectLabelBOList.stream().map(SubjectLabelBO::getLabelName).toList();
        subjectInfoBO.setLabelName(labelNameList);
        return subjectInfoBO;
    }
}
