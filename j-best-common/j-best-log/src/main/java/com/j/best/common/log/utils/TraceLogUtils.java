package com.j.best.common.log.utils;

import java.util.UUID;

public class TraceLogUtils {
    public static String getTraceId() {
        return UUID.randomUUID().toString();
    }
}
