package com.don.subject.application.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.don.subject.application.convert.SubjectAnswerDTOConverter;
import com.don.subject.application.convert.SubjectCategoryDTOConverter;
import com.don.subject.application.convert.SubjectInfoDTOConverter;
import com.don.subject.application.dto.SubjectCategoryDTO;
import com.don.subject.application.dto.SubjectInfoDTO;
import com.don.subject.common.entity.PageResult;
import com.don.subject.common.entity.Result;
import com.don.subject.common.enums.IsDeletedFlagEnum;
import com.don.subject.domain.convert.SubjectInfoConverter;
import com.don.subject.domain.entity.SubjectAnswerBO;
import com.don.subject.domain.entity.SubjectCategoryBO;
import com.don.subject.domain.entity.SubjectInfoBO;
import com.don.subject.domain.service.SubjectCategoryDomainService;
import com.don.subject.domain.service.SubjectInfoDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

//import javax.annotation.Resource;

/**
 * @author A
 * @date 2025/8/12
 **/
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {
//    @Resource
    @Autowired
    private SubjectInfoDomainService subjectInfoDomainService;

    /**
     * 新增分类
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkArgument(!StringUtils.isEmpty(subjectInfoDTO.getSubjectName()),"题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(),
                    "难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(),
                    "题目类型不能为空");
            Preconditions.checkArgument(subjectInfoDTO.getSubjectType() > 0
                    && subjectInfoDTO.getSubjectType() <= 4, " 题目类型错误");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(),
                    "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()),"分类ID不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()),"标签ID不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOtoInfoBO(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerDTOConverter.INSTANCE.convertDTOtoInfoBOList(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOList);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectCategoryController.add.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     *
     * 查询题目列表
     *
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> page(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectController.page.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(),
                    "分类 id 不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(),
                    "标签 id 不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOtoInfoBO(subjectInfoDTO);
            PageResult<SubjectInfoBO> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(boPageResult);
        }catch (Exception e){
            log.error("SubjectCategoryController.page.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     *
     * 查询题目信息
     *
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/querySubjectInfo")
    public Result<SubjectInfoDTO> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectController.page.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getId(),
                    "题目 id 不能为空");
//            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(),
//                    "分类 id 不能为空");
//            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(),
//                    "标签 id 不能为空");

            SubjectInfoBO subjectInfoBOParam = SubjectInfoDTOConverter.INSTANCE.convertDTOtoInfoBO(subjectInfoDTO);
            SubjectInfoBO subjectInfoBO = subjectInfoDomainService.querySubjectInfo(subjectInfoBOParam);
            SubjectInfoDTO subjectInfoDTOEntity = SubjectInfoDTOConverter.INSTANCE.convertBoToDtoInfo(subjectInfoBO);
            return Result.ok(subjectInfoDTOEntity);
        }catch (Exception e){
            log.error("SubjectCategoryController.page.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }
}
