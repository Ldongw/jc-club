package com.don.subject.domain.service;

import com.don.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @author A
 * @date 2025/8/12
 **/
public interface SubjectCategoryDomainService {
    /**
     * 新增分类
     * @param subjectCategoryBO
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位大类
     * @return
     */
    List<SubjectCategoryBO> queryPrimaryCategoryList(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     * @param subjectCategoryBO
     * @return
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询分类及标签
     * @param subjectCategoryBO
     * @return
     */
    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}
