package com.seeyou.toolkit.base;

import androidx.annotation.NonNull;

import java.util.List;

public interface DataSource<T> {

    void setData(@NonNull List<T> data);

    void clear();

    int size();

}
