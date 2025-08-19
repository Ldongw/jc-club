package com.don.subject.domain.handler.subject;

import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.common.enums.SubjectInfoTypeEnum;
import com.don.subject.domain.convert.BriefSubjectConverter;
import com.don.subject.domain.convert.JudgeSubjectConverter;
import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import com.don.subject.infra.basic.entity.SubjectBrief;
import com.don.subject.infra.basic.service.SubjectBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 简答题目策略类
 *
 * @author A
 * @date 2025/8/15
 **/
@Component
public class BriefTypeHandler implements SubjectTypeHandler{

    @Autowired
    private SubjectBriefService subjectBriefService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectBrief subjectBriefParam = new SubjectBrief();
        subjectBriefParam.setSubjectId(subjectId);
        SubjectBrief subjectBrief = subjectBriefService.queryByCondition(subjectBriefParam);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        return subjectOptionBO;
    }
}
