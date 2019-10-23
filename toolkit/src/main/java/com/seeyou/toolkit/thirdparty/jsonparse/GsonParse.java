package com.seeyou.toolkit.thirdparty.jsonparse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Gson解析实现类
 *
 * @author sumn
 * date 2018/8/15
 */
public class GsonParse implements IJsonParse {


    private Gson gson;

    /**
     * json数据转换为Object
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T parseObject(String json, Class<T> clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.fromJson(json, clazz);
    }

    /**
     * json数据转换为Object
     * @param json
     * @param typeOfT
     * @param <T>
     * @return
     */

    @Override
    public <T> T parseObject(String json, Type typeOfT) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.fromJson(json,typeOfT);
    }

    /**
     * json数据转化为ArrayList
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> ArrayList<T> parseToArrayList(String json, Class<T> clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = gson.fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(gson.fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    /**
     * Json数据转化为list
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> parseToList(String json, Class<T[]> clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    /**
     * object转化为json
     *
     * @param o
     * @return
     */
    @Override
    public String objectToJson(Object o) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.toJson(o);
    }
}
