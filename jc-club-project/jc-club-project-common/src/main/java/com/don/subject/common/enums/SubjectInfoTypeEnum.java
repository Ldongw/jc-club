package com.don.subject.common.enums;

/**
 * 分类类型
 * @author A
 * @date 2025/8/14
 **/
public enum SubjectInfoTypeEnum {

    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答");

    public int code;

    public String desc;

    SubjectInfoTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(int codeVal){
        for(SubjectInfoTypeEnum resultCodeEnum : SubjectInfoTypeEnum.values())
            if(resultCodeEnum.code == codeVal)
                return resultCodeEnum;
        return null;
    }
}
