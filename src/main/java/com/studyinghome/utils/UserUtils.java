package com.studyinghome.utils;

import com.studyinghome.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * UserUtils
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/30 10:40
 */
public class UserUtils {
    public static User getCurrentHr() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
