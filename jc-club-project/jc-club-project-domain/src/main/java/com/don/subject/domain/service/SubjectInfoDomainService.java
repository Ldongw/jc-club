package com.don.subject.domain.service;

import com.don.subject.common.entity.PageResult;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @author A
 * @date 2025/8/15
 **/
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);


    /**
     * 分页查询
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     * @param subjectInfoBOParam
     * @return
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBOParam);

    /**
     * 更新标签
     * @param subjectLabelBO
     * @return
     */
   /* Boolean update(SubjectLabelBO subjectLabelBO);


    Boolean delete(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
    */
}
