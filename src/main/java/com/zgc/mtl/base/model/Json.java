package com.zgc.mtl.base.model;

/**
 */
public class Json {
    private Integer total;
    private boolean success;
    private String msg;
    private Object data;

    public Json(){
    }

    public Json(boolean success,String msg,Object data){
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Json(boolean success,int total,Object data){
        this.success = success;
        this.total = total;
        this.data = data;
    }

    public Json(boolean success,String msg){
        this.success = success;
        this.msg = msg;
    }

    public Json(boolean success,Object data){
        this.success = success;
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
}
