package com.studyinghome.entity;

import com.studyinghome.business.entity.User;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-10 下午 4:10
 */
@Alias("user")
@Data
public class SysUser extends User {
}
