package com.studyinghome.mapper;

import com.studyinghome.model.Role;
import com.studyinghome.model.User;

import java.util.List;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:11
 */

public interface UserMapper extends BaseMapper<User> {

    User getByName(String name);

    List<Role> getRolesByUid(int uid);
}
