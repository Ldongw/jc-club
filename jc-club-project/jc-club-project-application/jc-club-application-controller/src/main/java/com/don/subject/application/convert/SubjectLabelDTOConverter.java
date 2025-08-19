package com.don.subject.application.convert;

import com.don.subject.application.dto.SubjectLabelDTO;
import com.don.subject.domain.entity.SubjectLabelBO;
import com.don.subject.infra.basic.entity.SubjectLabel;
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
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDTOtoLabelBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBOtoLabelDTOList(List<SubjectLabelBO> subjectLabelBOList);
}
