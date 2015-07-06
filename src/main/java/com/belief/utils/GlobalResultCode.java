package com.belief.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局结果定义，可以根据业务继续在此添加
 * 
 * @author 于东伟
 *
 */
public enum GlobalResultCode {
    /** 操作成功 */
    success("success", "操作成功"),
    /** 重复提交 */
    repeat("repeat", "重复提交"),
    /** 出现错误 */
    error("error", "出现错误"),
    /** 参数为空 */
    nullValue("nullValue", "参数为空");

    private String code;
    private String message;

    private GlobalResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String[] getCodes() {
        List<String> enums = new ArrayList<String>();
        for (GlobalResultCode s : GlobalResultCode.values()) {
            enums.add(s.getCode());
        }

        return enums.toArray(new String[] {});
    }

    public static GlobalResultCode getByCode(String code) {
        for (GlobalResultCode s : GlobalResultCode.values()) {
            if ((s.getCode()).equals(code)) {
                return s;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
