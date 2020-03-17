package com.studyinghome.entity;

import lombok.Data;

/**
 * Message
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-05-10 17:20
 */
@Data
public class Message {
    private String operate;
    private String message;

    public static Message getMessage(String operate, String message) {
        return new Message(operate, message);
    }

    private Message(String operate, String message) {
        this.operate = operate;
        this.message = message;
    }
}
