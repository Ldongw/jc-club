package com.don.subject.application.convert;

import com.don.subject.application.dto.SubjectCategoryDTO;
import com.don.subject.domain.entity.SubjectCategoryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-02T14:31:27+0800",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class SubjectCategoryDTOConverterImpl implements SubjectCategoryDTOConverter {

    @Override
    public SubjectCategoryBO convertDToBCategory(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );
        subjectCategoryBO.setCount( subjectCategoryDTO.getCount() );

        return subjectCategoryBO;
    }

    @Override
    public List<SubjectCategoryDTO> convertDToBCategory(List<SubjectCategoryBO> subjectCategoryBOList) {
        if ( subjectCategoryBOList == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryBOList.size() );
        for ( SubjectCategoryBO subjectCategoryBO : subjectCategoryBOList ) {
            list.add( converBOToCategoryDTO( subjectCategoryBO ) );
        }

        return list;
    }

    @Override
    public SubjectCategoryDTO converBOToCategoryDTO(SubjectCategoryBO categoryBO) {
        if ( categoryBO == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( categoryBO.getId() );
        subjectCategoryDTO.setCategoryName( categoryBO.getCategoryName() );
        subjectCategoryDTO.setCategoryType( categoryBO.getCategoryType() );
        subjectCategoryDTO.setImageUrl( categoryBO.getImageUrl() );
        subjectCategoryDTO.setParentId( categoryBO.getParentId() );
        subjectCategoryDTO.setCount( categoryBO.getCount() );

        return subjectCategoryDTO;
    }
}
