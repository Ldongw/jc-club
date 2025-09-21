package com.don.auth.common.entity;

/**
 *
 * 分页请求实体
 *
 * @author A
 * @date 2025/8/16
 **/
public class PageInfo {

    private Integer pageNo = 1;
    private  Integer pageSize = 20;

    public Integer getPageNo(){
        if(pageNo == null || pageNo < 1)
            return 1;
        return pageNo;
    }

    public Integer getPageSize(){
        if(pageSize == null || pageSize < 1)
            return 20;
        return pageSize;
    }
}
