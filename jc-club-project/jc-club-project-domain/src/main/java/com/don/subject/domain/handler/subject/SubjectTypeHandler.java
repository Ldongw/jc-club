package com.don.subject.domain.handler.subject;

import com.don.subject.common.enums.SubjectInfoTypeEnum;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.entity.SubjectOptionBO;
import org.springframework.stereotype.Component;

/**
 * @author A
 * @date 2025/8/15
 **/
public interface SubjectTypeHandler {

    /**
     * 枚举身份的识别
     * @return
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际题目插入
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 题目信息查询
     * @param subjectId
     */
    SubjectOptionBO query(Long subjectId);
}
