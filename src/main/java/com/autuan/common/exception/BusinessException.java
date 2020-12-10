package com.autuan.common.exception;

import com.autuan.common.enums.BusinessErrorEnum;

/**
 * 业务异常
 *
 * @author ruoyi
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected String code;
    protected final String message;

    public BusinessException(BusinessErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMsg();
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
