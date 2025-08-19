package com.don.subject.application.convert;

import com.don.subject.application.dto.SubjectInfoDTO;
import com.don.subject.application.dto.SubjectLabelDTO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectLabelBO;
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
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOtoInfoBO(SubjectInfoDTO subjectInfoDTO);

    List<SubjectInfoDTO> convertBOtoInfoDTOList(List<SubjectInfoBO> subjectInfoBOList);

    SubjectInfoDTO convertBoToDtoInfo(SubjectInfoBO subjectInfoBO);
}
