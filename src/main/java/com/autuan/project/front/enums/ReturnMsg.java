package com.autuan.project.front.enums;

public enum ReturnMsg {

    SUCCESS("请求成功！", "000000"),

    ERROR("请求失败！", "999999");

    // 成员变量
    private String errMsg;

    private String errCode;

    public String getErrMsg() {
        return errMsg;
    }

    public String getErrCode() {
        return errCode;
    }


    // 构造方法
    ReturnMsg(String errMsg, String errCode) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    // 覆盖方法
    @Override
    public String toString() {
        return this.errCode + "_" + this.errMsg;
    }
}
