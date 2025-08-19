package com.don.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目答案实体类
 *
 * @author makejava
 * @since 2025-08-15 20:00:46
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = 611943525792858182L;
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

