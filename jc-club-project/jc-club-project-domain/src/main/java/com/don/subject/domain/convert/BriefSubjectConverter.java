package com.don.subject.domain.convert;

import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.infra.basic.entity.SubjectBrief;
import com.don.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author A
 * @date 2025/8/15
 **/
@Mapper
public interface BriefSubjectConverter {
    BriefSubjectConverter INSTANCE = Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO);
}
