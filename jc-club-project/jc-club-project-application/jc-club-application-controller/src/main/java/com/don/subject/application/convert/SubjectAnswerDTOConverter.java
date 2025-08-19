package com.don.subject.application.convert;

import com.don.subject.application.dto.SubjectAnswerDTO;
import com.don.subject.application.dto.SubjectInfoDTO;
import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *
 * 标签 DTO 转换
 * @author A
 * @date 2025/8/15
 **/
@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDTOtoAnswerBO(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerDTO> convertBOtoInfoDTOList(List<SubjectAnswerBO> subjectAnswerBOList);

    List<SubjectAnswerBO> convertDTOtoInfoBOList(List<SubjectAnswerDTO> subjectAnswerDTOList);

}
