package com.dream.server.enums;

public enum SexEnum {

    MAN("M", "男性"),
    FEMALE("F", "女性");

    private String code;

    private String desc;

    SexEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
