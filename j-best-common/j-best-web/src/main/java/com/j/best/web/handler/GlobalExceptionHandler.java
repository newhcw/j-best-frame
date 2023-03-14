package com.j.best.web.handler;

import com.j.best.web.exception.GlobalException;
import com.j.best.web.util.CodeMsg;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public CodeMsg exceptionHandler(HttpServletRequest request, Exception e) {
        // 本地自定义异常处理
        if(e instanceof GlobalException){
            return ((GlobalException) e).getCodeMsg();
        }
        //绑定异常是需要明确提示给用户的
        else if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getAllErrors();
            String msg = errors.get(0).getDefaultMessage();//获取自错误信息
            return CodeMsg.SERVER_BIND_ERROR.fillArgs(msg);//将具体
            //错误信息设置到CodeMsg中返回
        }
        // 系统异常处理
        return CodeMsg.SERVER_BIND_ERROR.fillArgs(e.getCause() + e.getMessage());//将具体
    }
}

