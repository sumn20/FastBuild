package com.seeyou.toolkit.intfic;

/**
 * @author sumn
 * date 2019/10/21
 */
public interface NetWorkCallback<T> {
    void Success(T t);

    void Error(String msg);

    void Loading();

}
