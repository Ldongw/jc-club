package com.don.subject.domain.handler.subject;

import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.common.enums.SubjectInfoTypeEnum;
import com.don.subject.domain.convert.MultipleSubjectConverter;
import com.don.subject.domain.convert.RadioSubjectConverter;
import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import com.don.subject.infra.basic.entity.SubjectBrief;
import com.don.subject.infra.basic.entity.SubjectRadio;
import com.don.subject.infra.basic.service.SubjectRadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 单选题目策略类
 *
 * @author A
 * @date 2025/8/15
 **/
@Component
public class RadioTypeHandler implements SubjectTypeHandler{

    @Autowired
    private SubjectRadioService subjectRadioService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectRadio> subjectRadioList = new ArrayList<>();
        for (SubjectAnswerBO subjectAnswerBO : subjectInfoBO.getOptionList()) {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertBoToEntity(subjectAnswerBO);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
            subjectRadioList.add(subjectRadio);
        }
        subjectRadioService.insertBatch(subjectRadioList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(subjectId);
        List<SubjectRadio> subjectRadioList = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = RadioSubjectConverter.INSTANCE.convertMuLToAL(subjectRadioList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
