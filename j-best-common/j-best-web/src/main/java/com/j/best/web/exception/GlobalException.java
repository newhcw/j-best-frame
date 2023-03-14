package com.j.best.web.exception;

import com.j.best.web.util.CodeMsg;

public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg=codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}

