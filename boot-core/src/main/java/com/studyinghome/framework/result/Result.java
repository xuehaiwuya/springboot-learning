package com.studyinghome.framework.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * ${DESCRIPTION}
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:42
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /**
     * 成功时候调用
     */
    public static <T> Result<T> success(CodeMsg codeMsg, T data) {
        return new Result<>(codeMsg, data);
    }

    public static <T> Result<T> success(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 失败时候调用
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    private int code;
    private String msg;
    private T data;
    private Long timestamp;

    private Result(CodeMsg codeMsg, T data) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
        this.data = data;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    private Result(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
        this.timestamp = System.currentTimeMillis() / 1000;
    }
}
