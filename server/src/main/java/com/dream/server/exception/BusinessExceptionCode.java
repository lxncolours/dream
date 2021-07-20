package com.dream.server.exception;

public enum BusinessExceptionCode {

    MEMBER_NOT_EXIST("会员不存在"),
    USER_LOGIN_NAME_EXIST("用户账号已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    LOGIN_MEMBER_ERROR("手机号不存在或密码错误"),
    MOBILE_CODE_TOO_FREQUENT("短信请求过于频繁"),
    MOBILE_CODE_ERROR("短信验证码不正确"),
    MOBILE_CODE_EXPIRED("短信验证码不存在或已过期，请重新发送短信"),
    EXISTS_BAL("存在未消费余额,删除失败"),
    NOT_EXISTS_BAL("余额信息不存在"),
    NOT_MATCH_BAL("余额信息不匹配,请稍后再试"),
    EXISTS_CHILDREN("存在未删除的子分类,不允许删除父分类"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
