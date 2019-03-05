package com.business.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuozheng.hzz
 */
public class ResultCode {
    public static final int SUCCESS = 200;

    public static final int PARAM_INVALID = 400;

    public static final int PERMISSIONS_REJECTED = 403;

    public static final int UNKNOWN = 500;

    public static final int TASK_NOT_FOUND = 604;
    public static final int TASK_FAIL = 704;

    private static Map<Integer, String> DESC = new HashMap<>();

    static {
        DESC.put(SUCCESS, "success");
        DESC.put(PARAM_INVALID, "request params are invalid");
        DESC.put(UNKNOWN, "unknown");
        DESC.put(PERMISSIONS_REJECTED, "permissions are rejected");
        DESC.put(TASK_NOT_FOUND, "task not found");
        DESC.put(TASK_FAIL, "task report fail");


    }

    public static String getDesc(Integer code) {
        String desc = null;
        if (code != null) {
            desc = DESC.get(code);
        }
        return desc != null ? desc : "";
    }
}
