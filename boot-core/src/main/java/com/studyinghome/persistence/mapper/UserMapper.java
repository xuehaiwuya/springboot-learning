package com.studyinghome.persistence.mapper;

import com.studyinghome.business.entity.Role;
import com.studyinghome.business.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getByName(String name);

    List<Role> getRolesByUid(Long id);
}
