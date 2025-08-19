package com.don.subject.domain.service;

import com.don.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @author A
 * @date 2025/8/15
 **/
public interface SubjectLabelDomainService {

    /**
     * 新增标签
     * @param subjectLabelBO
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     * @param subjectLabelBO
     * @return
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    Boolean delete(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
