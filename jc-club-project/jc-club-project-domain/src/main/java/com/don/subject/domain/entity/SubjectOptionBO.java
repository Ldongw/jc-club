package com.don.subject.domain.entity;

import com.don.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author makejava
 * @since 2025-08-15 20:00:46
 */
@Data
public class SubjectOptionBO implements Serializable {

    private static final long serialVersionUID = 611943525792852282L;
    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

//    /**
//     * 是否正确
//     */
//    private Integer isCorrect;

}

