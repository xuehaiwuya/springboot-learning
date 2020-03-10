package com.studyinghome.framework.object;

import java.util.List;

/**
 * 公共service
 *
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-10 下午 5:27
 */

public interface AbstractService<T, PK> {
    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    T insert(T entity);


    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    boolean removeByPrimaryKey(PK primaryKey);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    boolean update(T entity);


    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    T getByPrimaryKey(PK primaryKey);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    T getOneByEntity(T entity);

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    List<T> listAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    List<T> listByEntity(T entity);
}
