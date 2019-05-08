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
    public static CodeMsg ERROR = new CodeMsg(100, "请求异常,请检查后重新提交");
    public static CodeMsg LOGIN_ERROR = new CodeMsg(101, "账户名或者密码输入错误");
    public static CodeMsg ACCOUNT_ERROR = new CodeMsg(102, "账户被禁用,请联系管理员");
    public static CodeMsg NEED_LOGIN = new CodeMsg(103, "账户未登录");
    public static CodeMsg SECURITY_ERROR = new CodeMsg(403, "权限不足");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500, "服务端异常");


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
