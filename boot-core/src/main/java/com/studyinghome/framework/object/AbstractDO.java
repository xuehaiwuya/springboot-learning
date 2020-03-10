package com.studyinghome.framework.object;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-10 下午 5:00
 */
@Data
public abstract class AbstractDO implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTime;
}
