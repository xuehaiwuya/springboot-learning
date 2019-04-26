package com.studyinghome.mapper;

import java.util.List;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:13
 */
public interface BaseMapper<T> {
    T getById(int id);

    List<T> getAll();

    int insert(T t);

    int update(T t);

    int delete(int id);
}
