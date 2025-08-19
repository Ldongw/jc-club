package com.don.subject.domain.convert;

import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import com.don.subject.infra.basic.entity.SubjectJudge;
import com.don.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author A
 * @date 2025/8/15
 **/
@Mapper
public interface JudgeSubjectConverter {
    JudgeSubjectConverter INSTANCE = Mappers.getMapper(JudgeSubjectConverter.class);

    SubjectJudge convertBoToEntity(SubjectInfoBO subjectInfoBO);

    List<SubjectAnswerBO> convertJToAList(List<SubjectJudge> subjectJudgeList);
}
