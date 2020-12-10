package com.autuan.project.front.entity;


import com.autuan.common.enums.BusinessErrorEnum;
import com.autuan.project.front.enums.ReturnMsg;
import lombok.Data;

@Data
public class ReturnResult {

    private Object data;
    private String code;
    private String msg;

    /**
     * 自定义code,msg,data
     * @param code
     * @param msg
     * @param data
     */
    private ReturnResult(String code, String msg, Object data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 自定义data
     * @param data
     */
    private ReturnResult(Object data) {
        this.data = data;
        this.code = ReturnMsg.SUCCESS.getErrCode();
        this.msg = ReturnMsg.SUCCESS.getErrMsg();
    }

    /**
     * 自定义code,msg
     * @param code
     * @param msg
     */
    private ReturnResult(String code, String msg){
        this.code =  code;
        this.msg = msg;
    }

    private ReturnResult() {
        this.code = ReturnMsg.SUCCESS.getErrCode();
        this.msg = ReturnMsg.SUCCESS.getErrMsg();
    }
    /***
     * 输入参数:
     *
     *
     * @param data
     * @param code
     * @param msg
     * @return 返回成功，包含total、code、msg、data
     */
    public static ReturnResult ok(String code, String msg,Object data) {
        return new ReturnResult(code,msg,data);
    }

    public static ReturnResult ok(String code,String msg){
        return new ReturnResult(code,msg);
    }

    public static ReturnResult error (String code,String msg){
        return new ReturnResult(code,msg);
    }

    public static ReturnResult error (String msg){
        return new ReturnResult(ReturnMsg.ERROR.getErrCode(),msg);
    }

    /**
     * 枚举方式错误信息
     */
    public static ReturnResult error(BusinessErrorEnum errorEnum) {
        return new ReturnResult(errorEnum.getCode(),errorEnum.getMsg());
    }

    /***
     * 输入参数：
     *
     * @param data
     * @return 返回成功，包含code、msg、data
     */
    public static ReturnResult ok(Object data) {
        return new ReturnResult(data);
    }

    public static ReturnResult ok(){
        return new ReturnResult();
    }

}
