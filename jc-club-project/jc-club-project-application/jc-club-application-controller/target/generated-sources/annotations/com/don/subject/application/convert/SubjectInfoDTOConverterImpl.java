package com.don.subject.application.convert;

import com.don.subject.application.dto.SubjectAnswerDTO;
import com.don.subject.application.dto.SubjectInfoDTO;
import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-02T14:31:27+0800",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class SubjectInfoDTOConverterImpl implements SubjectInfoDTOConverter {

    @Override
    public SubjectInfoBO convertDTOtoInfoBO(SubjectInfoDTO subjectInfoDTO) {
        if ( subjectInfoDTO == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        subjectInfoBO.setId( subjectInfoDTO.getId() );
        subjectInfoBO.setSubjectName( subjectInfoDTO.getSubjectName() );
        subjectInfoBO.setSubjectDifficult( subjectInfoDTO.getSubjectDifficult() );
        subjectInfoBO.setSettleName( subjectInfoDTO.getSettleName() );
        subjectInfoBO.setSubjectType( subjectInfoDTO.getSubjectType() );
        subjectInfoBO.setSubjectScore( subjectInfoDTO.getSubjectScore() );
        subjectInfoBO.setSubjectParse( subjectInfoDTO.getSubjectParse() );
        subjectInfoBO.setSubjectAnswer( subjectInfoDTO.getSubjectAnswer() );
        List<Long> list = subjectInfoDTO.getCategoryIds();
        if ( list != null ) {
            subjectInfoBO.setCategoryIds( new ArrayList<Long>( list ) );
        }
        List<Long> list1 = subjectInfoDTO.getLabelIds();
        if ( list1 != null ) {
            subjectInfoBO.setLabelIds( new ArrayList<Long>( list1 ) );
        }
        subjectInfoBO.setOptionList( subjectAnswerDTOListToSubjectAnswerBOList( subjectInfoDTO.getOptionList() ) );
        subjectInfoBO.setCategoryId( subjectInfoDTO.getCategoryId() );
        subjectInfoBO.setLabelId( subjectInfoDTO.getLabelId() );
        List<String> list3 = subjectInfoDTO.getLabelName();
        if ( list3 != null ) {
            subjectInfoBO.setLabelName( new ArrayList<String>( list3 ) );
        }
        subjectInfoBO.setCreatedBy( subjectInfoDTO.getCreatedBy() );
        subjectInfoBO.setCreatedTime( subjectInfoDTO.getCreatedTime() );
        subjectInfoBO.setUpdateBy( subjectInfoDTO.getUpdateBy() );
        subjectInfoBO.setUpdateTime( subjectInfoDTO.getUpdateTime() );
        subjectInfoBO.setPageNo( subjectInfoDTO.getPageNo() );
        subjectInfoBO.setPageSize( subjectInfoDTO.getPageSize() );
        subjectInfoBO.setIsDeleted( subjectInfoDTO.getIsDeleted() );

        return subjectInfoBO;
    }

    @Override
    public List<SubjectInfoDTO> convertBOtoInfoDTOList(List<SubjectInfoBO> subjectInfoBOList) {
        if ( subjectInfoBOList == null ) {
            return null;
        }

        List<SubjectInfoDTO> list = new ArrayList<SubjectInfoDTO>( subjectInfoBOList.size() );
        for ( SubjectInfoBO subjectInfoBO : subjectInfoBOList ) {
            list.add( convertBoToDtoInfo( subjectInfoBO ) );
        }

        return list;
    }

    @Override
    public SubjectInfoDTO convertBoToDtoInfo(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectInfoDTO subjectInfoDTO = new SubjectInfoDTO();

        subjectInfoDTO.setId( subjectInfoBO.getId() );
        subjectInfoDTO.setSubjectName( subjectInfoBO.getSubjectName() );
        subjectInfoDTO.setSubjectDifficult( subjectInfoBO.getSubjectDifficult() );
        subjectInfoDTO.setSettleName( subjectInfoBO.getSettleName() );
        subjectInfoDTO.setSubjectType( subjectInfoBO.getSubjectType() );
        subjectInfoDTO.setSubjectScore( subjectInfoBO.getSubjectScore() );
        subjectInfoDTO.setSubjectParse( subjectInfoBO.getSubjectParse() );
        subjectInfoDTO.setSubjectAnswer( subjectInfoBO.getSubjectAnswer() );
        List<Long> list = subjectInfoBO.getCategoryIds();
        if ( list != null ) {
            subjectInfoDTO.setCategoryIds( new ArrayList<Long>( list ) );
        }
        List<Long> list1 = subjectInfoBO.getLabelIds();
        if ( list1 != null ) {
            subjectInfoDTO.setLabelIds( new ArrayList<Long>( list1 ) );
        }
        subjectInfoDTO.setOptionList( subjectAnswerBOListToSubjectAnswerDTOList( subjectInfoBO.getOptionList() ) );
        subjectInfoDTO.setCategoryId( subjectInfoBO.getCategoryId() );
        subjectInfoDTO.setLabelId( subjectInfoBO.getLabelId() );
        subjectInfoDTO.setCreatedBy( subjectInfoBO.getCreatedBy() );
        subjectInfoDTO.setCreatedTime( subjectInfoBO.getCreatedTime() );
        subjectInfoDTO.setUpdateBy( subjectInfoBO.getUpdateBy() );
        subjectInfoDTO.setUpdateTime( subjectInfoBO.getUpdateTime() );
        List<String> list3 = subjectInfoBO.getLabelName();
        if ( list3 != null ) {
            subjectInfoDTO.setLabelName( new ArrayList<String>( list3 ) );
        }
        subjectInfoDTO.setIsDeleted( subjectInfoBO.getIsDeleted() );

        return subjectInfoDTO;
    }

    protected SubjectAnswerBO subjectAnswerDTOToSubjectAnswerBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }

    protected List<SubjectAnswerBO> subjectAnswerDTOListToSubjectAnswerBOList(List<SubjectAnswerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerBO> list1 = new ArrayList<SubjectAnswerBO>( list.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : list ) {
            list1.add( subjectAnswerDTOToSubjectAnswerBO( subjectAnswerDTO ) );
        }

        return list1;
    }

    protected SubjectAnswerDTO subjectAnswerBOToSubjectAnswerDTO(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectAnswerDTO subjectAnswerDTO = new SubjectAnswerDTO();

        subjectAnswerDTO.setOptionType( subjectAnswerBO.getOptionType() );
        subjectAnswerDTO.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectAnswerDTO.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectAnswerDTO;
    }

    protected List<SubjectAnswerDTO> subjectAnswerBOListToSubjectAnswerDTOList(List<SubjectAnswerBO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerDTO> list1 = new ArrayList<SubjectAnswerDTO>( list.size() );
        for ( SubjectAnswerBO subjectAnswerBO : list ) {
            list1.add( subjectAnswerBOToSubjectAnswerDTO( subjectAnswerBO ) );
        }

        return list1;
    }
}
