package com.j.best.web.repeat;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Repeat {
    long value() default 3;//默认3秒
}
