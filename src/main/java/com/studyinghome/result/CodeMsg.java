package com.studyinghome.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义返回请求结果格式
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:48
 */
@Getter
@Setter
@ToString
public class CodeMsg {
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg ERROR = new CodeMsg(100, "请求异常");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500, "服务端异常");
    public static CodeMsg SECURITY_ERROR = new CodeMsg(600, "权限不足");


    int code;
    String msg;

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
