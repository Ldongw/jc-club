package com.don.subject.application.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.don.subject.application.convert.SubjectCategoryDTOConverter;
import com.don.subject.application.dto.SubjectCategoryDTO;
import com.don.subject.common.entity.Result;
import com.don.subject.domain.convert.SubjectCategoryConverter;
import com.don.subject.domain.entity.SubjectCategoryBO;
import com.don.subject.domain.service.SubjectCategoryDomainService;
import com.don.subject.infra.basic.entity.SubjectCategory;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//import javax.annotation.Resource;

/**
 * @author A
 * @date 2025/8/12
 **/
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {
//    @Resource
    @Autowired
    private SubjectCategoryDomainService subjectCategoryServiceDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryDomainService.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isEmpty(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父级id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDToBCategory(subjectCategoryDTO);
            subjectCategoryServiceDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectCategoryController.add.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/queryPrimaryCategoryList")
    public Result<SubjectCategoryDTO> queryPrimaryCategoryList(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryDomainService.queryPrimaryCategoryList");
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDToBCategory(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryServiceDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertDToBCategory(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.queryPrimaryCategoryList.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }
    @PostMapping("/queryCategoryByPrimary")
    public Result<SubjectCategoryDTO> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryDomainService.queryCategoryByPrimary.dto:{}",JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父级ID不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDToBCategory(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryServiceDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertDToBCategory(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.queryCategoryByPrimary.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryDomainService.update.dto:{}",JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"ID不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDToBCategory(subjectCategoryDTO);
            Boolean result = subjectCategoryServiceDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectCategoryController.update.error:{}",e.getMessage());
            return Result.fail("更新分类失败");
        }
    }
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryDomainService.delete.dto:{}",JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"ID不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDToBCategory(subjectCategoryDTO);
            Boolean result = subjectCategoryServiceDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectCategoryController.delete.error:{}",e.getMessage());
            return Result.fail("删除分类失败");
        }
    }
}
