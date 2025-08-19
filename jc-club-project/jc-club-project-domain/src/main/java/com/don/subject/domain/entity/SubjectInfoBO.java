package com.don.subject.domain.entity;

import com.don.subject.common.entity.PageInfo;
import lombok.Data;

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
public class SubjectInfoBO extends PageInfo implements Serializable {
    private static final long serialVersionUID = 611943525792858182L;
/**
     * 主键
     */
    private Long id;
/**
     * 题目名称
     */
    private String subjectName;
/**
     * 题目难度
     */
    private Integer subjectDifficult;
/**
     * 出题人名
     */
    private String settleName;
/**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
/**
     * 题目分数
     */
    private Integer subjectScore;
/**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 分类id
     */
    private List<Long> categoryIds;

    /**
     * 标签id
     */
    private List<Long> labelIds;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

    /**
     * 分类 id
     */
    private Long categoryId;

    /**
     * 标签 id
     */
    private Long labelId;

    /**
     * 标签name
     */
    List<String> labelName;

/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 修改人
     */
    private String updateBy;
/**
     * 修改时间
     */
    private Date updateTime;

    private Integer pageNo;

    private Integer pageSize;

    private Integer isDeleted;

}

