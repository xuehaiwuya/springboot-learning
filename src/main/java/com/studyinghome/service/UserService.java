package com.studyinghome.service;

import com.studyinghome.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:16
 */
public interface UserService extends UserDetailsService {
    User getUserById(int id);

    int save(User user);

    int update(User user);

    int delete(int id);
}
