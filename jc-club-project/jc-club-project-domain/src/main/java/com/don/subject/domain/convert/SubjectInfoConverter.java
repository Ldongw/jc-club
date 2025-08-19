package com.don.subject.domain.convert;

import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectLabelBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import com.don.subject.infra.basic.entity.SubjectInfo;
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
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO convertOpAndInfoToBo(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);

    List<SubjectInfoBO> convertToBoInfo(List<SubjectInfo> subjectInfoList);

}
