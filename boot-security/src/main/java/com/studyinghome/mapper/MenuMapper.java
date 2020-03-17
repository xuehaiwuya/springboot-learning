package com.studyinghome.mapper;

import com.studyinghome.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-29 11:41
 */
@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();
}
