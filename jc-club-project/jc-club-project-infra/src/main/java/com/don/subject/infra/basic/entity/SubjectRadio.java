package com.don.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 单选题信息表(SubjectRadio)实体类
 *
 * @author makejava
 * @since 2025-08-15 20:03:44
 */
@Data
public class SubjectRadio implements Serializable {
    private static final long serialVersionUID = 622000008977266411L;
/**
     * 主键
     */
    private Long id;
/**
     * 题目id
     */
    private Long subjectId;
/**
     * a,b,c,d
     */
    private Integer optionType;
/**
     * 选项内容
     */
    private String optionContent;
/**
     * 是否正确
     */
    private Integer isCorrect;
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

    private Integer isDeleted;

}

