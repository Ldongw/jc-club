package com.don.subject.common.enums;

/**
 * 分类类型
 * @author A
 * @date 2025/8/14
 **/
public enum CategoryTypeEnum {

    PRIMARY(1,"岗位大类"),
    SECOND(2,"二级分类");

    public int code;

    public String desc;

    CategoryTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static CategoryTypeEnum getByCode(int codeVal){
        for(CategoryTypeEnum resultCodeEnum : CategoryTypeEnum.values())
            if(resultCodeEnum.code == codeVal)
                return resultCodeEnum;
        return null;
    }
}
