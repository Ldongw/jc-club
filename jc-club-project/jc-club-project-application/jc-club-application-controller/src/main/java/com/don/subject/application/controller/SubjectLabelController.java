package com.don.subject.application.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.don.subject.application.convert.SubjectCategoryDTOConverter;
import com.don.subject.application.convert.SubjectLabelDTOConverter;
import com.don.subject.application.dto.SubjectCategoryDTO;
import com.don.subject.application.dto.SubjectLabelDTO;
import com.don.subject.common.entity.Result;
import com.don.subject.domain.entity.SubjectCategoryBO;
import com.don.subject.domain.entity.SubjectLabelBO;
import com.don.subject.domain.service.SubjectLabelDomainService;
import com.don.subject.infra.basic.service.SubjectLabelService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签 Controller
 *
 * @author A
 * @date 2025/8/15
 **/
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Autowired
    private SubjectLabelDomainService subjectLabelDomainService;


    /**
     * 新增标签
     * @param subjectLabelDTO
     * @return
     */

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkArgument(!StringUtils.isEmpty(subjectLabelDTO.getLabelName()),"标签名称不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOtoLabelBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.add.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新标签
     * @param subjectLabelDTO
     * @return
     */

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.update.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"ID不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOtoLabelBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.update.error:{}",e.getMessage());
            return Result.fail("更新标签失败");
        }
    }

    /**
     * 删除标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.delete.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"ID不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOtoLabelBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.delete.error:{}",e.getMessage());
            return Result.fail("删除标签失败");
        }
    }

    /**
     * 查询分类下标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}",
                        JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"分类ID不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOtoLabelBO(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConverter.INSTANCE.convertBOtoLabelDTOList(subjectLabelBOList);
            return Result.ok(subjectLabelDTOS);
        }catch (Exception e){
            log.error("SubjectLabelController.queryLabelByCategoryId.error:{}",e.getMessage());
            return Result.fail("查询分类下标签失败");
        }
    }
}
