package com.j.best.web.util;

public class CodeMsg {

    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    //注意  %s ，格式化字符串
    public static CodeMsg SERVER_BIND_ERROR = new CodeMsg(500101, "服务端绑定异常:%s");
    //登录模块 5002XX
    public static CodeMsg MSG_NAME_IS_EMPTY = new CodeMsg(500200, "用户名不能为空！");
    public static CodeMsg MSG_PASSWORD_IS_EMPTY = new CodeMsg(500201, "密码不能为空！");
    public static CodeMsg MSG_MOBILE_ERROR = new CodeMsg(500202, "手机号格式不正确！");
    public static CodeMsg MSG_MOBILE_IS_EMPTY = new CodeMsg(500203, "手机号不能为空！");
    public static CodeMsg	MSG_MOBILE_NOT_EXIST = new CodeMsg(500204, "手机号不存在！");
    public static CodeMsg	MSG_PASSWORD_ERROR = new CodeMsg(500205, "密码错误！");


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public CodeMsg fillArgs(Object ... args){
        int code=this.code;
        String message=String.format(msg,args);
        return new CodeMsg(code,message);
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

}
