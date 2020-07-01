package com.ideatest.demo.exception;

/**
 * 自定义异常
 * @author ziran
 * @date 2020/6/11-10:33
 */
public class MyErrorException extends RuntimeException {
    private static final long serialVersionUID = -7480022450501760611L;
    /**
     * 异常码
     */
    private String code;

    /**
     * 异常提示信息
     */
    private String message;

    public MyErrorException(ErrorMsgEnum ErrorMsgEnum) {
        this.code = ErrorMsgEnum.code();
        this.message = ErrorMsgEnum.msg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
