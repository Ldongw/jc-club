package com.don.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案实体类
 *
 * @author makejava
 * @since 2025-08-15 20:00:46
 */
@Data
public class SubjectAnswerBO implements Serializable {
    private static final long serialVersionUID = 611943525792858282L;
/**
     * 答案选项标识
     */
    private Long optionType;
/**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;
}

