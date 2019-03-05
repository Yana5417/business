package com.business.domain;

import java.io.Serializable;

/**
 * @author zuozheng.hzz
 */
public class ResResult implements Serializable {
    private static final long serialVersionUID = 1414336137774689931L;
    private boolean result;
    private Integer code;
    private String msg;
    private Object data;


    private Integer total;

    public ResResult(Integer code, String msg, Object data) {
        setCode(code);
        this.msg = msg;
        this.data = data;
    }
    public ResResult(Integer code, Object data, Integer total){
        setCode(code);
        this.data = data;
        this.total = total;
    }

    public ResResult() {
    }

    public ResResult(Integer code, Object data) {
        setCode(code);
        if (code != null && code.equals(ResultCode.SUCCESS)) {
            this.data = data;
        } else {
            this.msg = data == null ? ResultCode.getDesc(code) : data.toString();
        }
    }

    public ResResult(Integer code) {
        setCode(code);
        this.msg = ResultCode.getDesc(code);
    }

    public void setCode(Integer code) {
        this.code = code;
        if (code != null && code.equals(ResultCode.SUCCESS)) {
            this.result = true;
        }
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
