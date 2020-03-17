package com.studyinghome.dao;

import com.studyinghome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-17 下午 1:59
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
