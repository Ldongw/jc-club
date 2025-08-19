package com.don.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.domain.convert.SubjectCategoryConverter;
import com.don.subject.domain.convert.SubjectLabelConverter;
import com.don.subject.domain.entity.SubjectLabelBO;
import com.don.subject.domain.service.SubjectLabelDomainService;
import com.don.subject.infra.basic.entity.SubjectCategory;
import com.don.subject.infra.basic.entity.SubjectLabel;
import com.don.subject.infra.basic.entity.SubjectMapping;
import com.don.subject.infra.basic.service.SubjectLabelService;
import com.don.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author A
 * @date 2025/8/15
 **/

@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {


    @Autowired
    private SubjectLabelService subjectLabelService;

    @Autowired
    private SubjectMappingService subjectMappingService;

    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainService.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.
                convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int cnt = subjectLabelService.insert(subjectLabel);
        return cnt > 0;
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainService.update.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.
                convertBoToLabel(subjectLabelBO);
        int cnt = subjectLabelService.update(subjectLabel);
        return cnt > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainService.delete.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.
                convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int cnt = subjectLabelService.update(subjectLabel);
        return cnt > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(subjectLabelBO.getCategoryId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        if(CollectionUtils.isEmpty(subjectMappingList)){
            return Collections.emptyList();
        }
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).toList();
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> subjectLabelBOList = SubjectLabelConverter.INSTANCE.convertToBoLabel(subjectLabelList);
        return subjectLabelBOList;
    }
}
