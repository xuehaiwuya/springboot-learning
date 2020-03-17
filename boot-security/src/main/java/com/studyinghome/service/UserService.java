package com.studyinghome.service;

import com.studyinghome.entity.SecurityUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:16
 */
public interface UserService extends UserDetailsService {
    SecurityUser getUserById(Long id);
}
