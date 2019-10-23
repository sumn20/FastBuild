package com.seeyou.toolkit.thirdparty.jsonparse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Json解析的第三方库分离
 *
 * @author sumn
 * date 2018/8/15
 */
public interface IJsonParse {
    /**
     * json->实体类
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T parseObject(String json, Class<T> clazz);

    /**
     * json->实体类
     *
     * @param json
     * @param typeOfT
     * @param <T>
     * @return
     */
    <T> T parseObject(String json, Type typeOfT);

    /**
     * json->list
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */

    <T> ArrayList<T> parseToArrayList(String json, Class<T> clazz);

    /**
     * json->list
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */

    <T> List<T> parseToList(String json, Class<T[]> clazz);

    /**
     * 实体类->json
     *
     * @param o
     * @return
     */
    String objectToJson(Object o);

}
