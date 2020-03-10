package com.studyinghome.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JsonUtil
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/5/8 9:46
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象为空的字段不列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //把空的数组“[]”转为空
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    /**
     * 美化输出json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    /**
     * json转JavaBean
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * 与javaBean json数组字符串转换为列表
     */
    public static <T> List<T> string2list(String str, Class<T> clazz) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            List<T> list = objectMapper.readValue(str, javaType);
            return list;
        } catch (Exception e) {
            log.warn("Parse String to List error", e);
            return null;
        }
    }

    /**
     * json字符串转换为map
     */
    public static <T> Map<String, T> string2map(String str, Class<T> clazz) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            Map<String, Map<String, Object>> map = (Map<String, Map<String, Object>>) objectMapper.readValue(str, new TypeReference<Map<String, T>>() {
            });
            Map<String, T> result = Maps.newHashMap();
            for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
            }
            return result;
        } catch (Exception e) {
            log.warn("Parse String to Map error", e);
            return null;
        }
    }

    /**
     * 深度转换json成map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2mapDeeply(String json) {
        return json2MapRecursion(json, objectMapper);
    }

    /**
     * json转JavaBean
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        JavaType javaType = getCollectionType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * 把json解析成list，如果list内部的元素存在jsonString，继续解析
     *
     * @param json
     * @param mapper 解析工具
     * @return
     * @throws Exception
     */
    private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            List<Object> list = mapper.readValue(json, List.class);
            for (Object obj : list) {
                if (obj != null && obj instanceof String) {
                    String str = (String) obj;
                    if (str.startsWith("[")) {
                        obj = json2ListRecursion(str, mapper);
                    } else if (obj.toString().startsWith("{")) {
                        obj = json2MapRecursion(str, mapper);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            log.warn("Parse String to Map error", e);
            return null;
        }
    }

    /**
     * 把json解析成map，如果map内部的value存在jsonString，继续解析
     *
     * @param json
     * @param mapper
     * @return
     * @throws Exception
     */
    private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            Map<String, Object> map = mapper.readValue(json, Map.class);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object obj = entry.getValue();
                if (obj != null && obj instanceof String) {
                    String str = ((String) obj);
                    if (str.startsWith("[")) {
                        List<?> list = json2ListRecursion(str, mapper);
                        map.put(entry.getKey(), list);
                    } else if (str.startsWith("{")) {
                        Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
                        map.put(entry.getKey(), mapRecursion);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            log.warn("Parse String to Map error", e);
            return null;
        }
    }

    /**
     * map转JavaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
