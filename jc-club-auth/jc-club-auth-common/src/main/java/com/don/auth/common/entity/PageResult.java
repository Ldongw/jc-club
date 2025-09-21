package com.don.auth.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * 分页返回实体
 *
 * @author A
 * @date 2025/8/16
 **/
@Data
public class PageResult<T> implements Serializable {

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    private Integer total = 0;

    private Integer totalPages = 0;

    private List<T> result = Collections.emptyList();

    private Integer start = 1;

    private Integer end = 0;

    public void setRecords(List<T> result){
        this.result = result;
        if(result != null && !result.isEmpty())
            setTotal(result.size());
    }

    public void setTotal(Integer total){
        this.total = total;
        if(this.pageSize > 0)
            this.totalPages = (total + this.pageSize - 1) / this.pageSize;
        else{
            this.totalPages = 0;
        }
        this.start = Math.max(this.pageNo - 1, 0) * this.pageSize + 1;
        this.end = this.start - 1 + this.pageSize;
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
    }
}
