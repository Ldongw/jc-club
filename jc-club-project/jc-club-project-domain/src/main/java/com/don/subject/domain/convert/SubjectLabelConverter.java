package com.don.subject.domain.convert;

import com.don.subject.domain.entity.SubjectCategoryBO;
import com.don.subject.domain.entity.SubjectLabelBO;
import com.don.subject.infra.basic.entity.SubjectCategory;
import com.don.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

//import javax.annotation.Resource;

/**
 * @author A
 * @date 2025/8/12
 **/
@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertToBoLabel(List<SubjectLabel> subjectLabelList);

}
