package com.studyinghome.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ${DESCRIPTION}
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-05-10 17:20
 */
@Getter
@Setter
public class Message<T> {
    private Integer code;
    private T msg;

    public static <T> Message<T> getMessage(T t) {
        return new Message(t);
    }

    Message(T t) {
        this.code = 200;
        this.msg = t;
    }
}
