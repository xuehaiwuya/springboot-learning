package com.studyinghome.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:13
 */
public interface BaseMapper<T> {
    T getById(Long id);

    List<T> getAll();

    Boolean insert(T t);

    Boolean update(T t);

    Boolean delete(Long id);
}
