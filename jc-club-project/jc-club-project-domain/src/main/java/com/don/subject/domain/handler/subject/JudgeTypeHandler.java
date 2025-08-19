package com.don.subject.domain.handler.subject;

import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.common.enums.SubjectInfoTypeEnum;
import com.don.subject.domain.convert.JudgeSubjectConverter;
import com.don.subject.domain.convert.SubjectInfoConverter;
import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import com.don.subject.infra.basic.entity.SubjectBrief;
import com.don.subject.infra.basic.entity.SubjectJudge;
import com.don.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 判断题目策略类
 *
 * @author A
 * @date 2025/8/15
 **/
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{

    @Autowired
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //SubjectJudge subjectJudge = JudgeSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().getFirst();
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectJudgeService.insert(subjectJudge);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectJudge subjectJudgeParam = new SubjectJudge();
        subjectJudgeParam.setSubjectId(subjectId);
        List<SubjectJudge> subjectJudgeList = subjectJudgeService.queryByCondition(subjectJudgeParam);
        List<SubjectAnswerBO> subjectAnswerBOList = JudgeSubjectConverter.INSTANCE.convertJToAList(subjectJudgeList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
