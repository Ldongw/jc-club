package com.don.subject.application.convert;

import com.don.subject.application.dto.SubjectCategoryDTO;
import com.don.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author A
 * @date 2025/8/12
 **/
@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDToBCategory(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertDToBCategory(List<SubjectCategoryBO> subjectCategoryBOList);

    SubjectCategoryDTO converBOToCategoryDTO(SubjectCategoryBO categoryBO);
}
