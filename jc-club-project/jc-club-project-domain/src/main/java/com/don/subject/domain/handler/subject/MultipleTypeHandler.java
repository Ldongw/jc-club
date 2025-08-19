package com.don.subject.domain.handler.subject;

import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.common.enums.SubjectInfoTypeEnum;
import com.don.subject.domain.convert.MultipleSubjectConverter;
import com.don.subject.domain.convert.RadioSubjectConverter;
import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import com.don.subject.infra.basic.entity.SubjectBrief;
import com.don.subject.infra.basic.entity.SubjectMultiple;
import com.don.subject.infra.basic.entity.SubjectRadio;
import com.don.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 多选题目策略类
 *
 * @author A
 * @date 2025/8/15
 **/

@Component
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Autowired
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectMultiple> subjectMultipleList = new ArrayList<>();
        for (SubjectAnswerBO subjectAnswerBO : subjectInfoBO.getOptionList()) {
            SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToEntity(subjectAnswerBO);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
            subjectMultipleList.add(subjectMultiple);
        }
        subjectMultipleService.insertBatch(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        List<SubjectMultiple> subjectMultipleList = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = MultipleSubjectConverter.INSTANCE.convertMuLToAL(subjectMultipleList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
