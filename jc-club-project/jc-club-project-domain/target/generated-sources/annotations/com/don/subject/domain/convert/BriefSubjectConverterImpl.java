package com.don.subject.domain.convert;

import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.infra.basic.entity.SubjectBrief;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T15:12:25+0800",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class BriefSubjectConverterImpl implements BriefSubjectConverter {

    @Override
    public SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectInfoBO.getId() );
        subjectBrief.setSubjectAnswer( subjectInfoBO.getSubjectAnswer() );
        subjectBrief.setCreatedBy( subjectInfoBO.getCreatedBy() );
        subjectBrief.setCreatedTime( subjectInfoBO.getCreatedTime() );
        subjectBrief.setUpdateBy( subjectInfoBO.getUpdateBy() );
        subjectBrief.setUpdateTime( subjectInfoBO.getUpdateTime() );
        subjectBrief.setIsDeleted( subjectInfoBO.getIsDeleted() );

        return subjectBrief;
    }
}
