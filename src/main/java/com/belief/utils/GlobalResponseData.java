package com.belief.utils;

/**
 * 全局返回结果
 * 
 * @author YuDongwei
 *
 * @param <T>
 */
public class GlobalResponseData<T> {
    private GlobalResultCode code;
    private T data;

    public GlobalResultCode getCode() {
        return code;
    }

    public void setCode(GlobalResultCode code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



}
