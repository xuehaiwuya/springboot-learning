package com.studyinghome.mapper;

import com.studyinghome.entity.SysUser;
import com.studyinghome.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:11
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    SysUser getByName(String name);
}
