package com.don.subject.domain.convert;

import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author A
 * @date 2025/8/15
 **/
@Mapper
public interface RadioSubjectConverter {
    RadioSubjectConverter INSTANCE = Mappers.getMapper(RadioSubjectConverter.class);

    SubjectRadio convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertMuLToAL(List<SubjectRadio> subjectRadioList);
}
