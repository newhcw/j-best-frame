package com.j.best.user.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
public class ResultHttpResponse<T> {

    private int code;

    private String message;

    private T data;

    public static <T>  ResultHttpResponse setData(T data){
        return ResultHttpResponse.builder().data(data).build();
    }
}
