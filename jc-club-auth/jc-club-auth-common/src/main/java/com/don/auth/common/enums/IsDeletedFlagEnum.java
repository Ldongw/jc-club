package com.don.auth.common.enums;

import lombok.Getter;

/**
 * Description: 删除枚举
 * @author A
 * @date 2025/8/14
 **/
@Getter
public enum IsDeletedFlagEnum {

    DELETED(1,"已删除"),
    UN_DELETE(0,"未删除");

    public int code;

    public String desc;

    IsDeletedFlagEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static IsDeletedFlagEnum getByCode(int codeVal){
        for(IsDeletedFlagEnum resultCodeEnum : IsDeletedFlagEnum.values())
            if(resultCodeEnum.code == codeVal)
                return resultCodeEnum;
        return null;
    }
}
