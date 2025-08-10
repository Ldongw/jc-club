package com.don.subject.application.controller;

import com.don.subject.infra.basic.entity.SubjectCategory;
import com.don.subject.infra.basic.service.SubjectCategoryService;
//import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.annotation.Resource;

/**
 * 刷题Controller
 *
 * @author A
 * @date 2025/8/10
 **/
@RestController
public class SubjectController {

//    @Resource
    @Autowired
    private SubjectCategoryService subjectCategoryService;
    @GetMapping("/test")
    public String test(){
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }
}
