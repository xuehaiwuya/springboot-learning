package com.studyinghome.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-05-10 17:20
 */
@Getter
@Setter
@NoArgsConstructor
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
