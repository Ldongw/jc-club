package com.don.subject.domain.convert;

import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.infra.basic.entity.SubjectMultiple;
import com.don.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author A
 * @date 2025/8/15
 **/
@Mapper
public interface MultipleSubjectConverter {
    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    SubjectMultiple convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertMuLToAL(List<SubjectMultiple> subjectMultipleList);
}
