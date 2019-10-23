package com.seeyou.toolkit.view;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * @author sumn
 * date 2019/10/21
 */
public class Action {
    private String title;
    private View.OnClickListener action;

    public Action(@NonNull String title, @NonNull View.OnClickListener action) {
        this.title = title;
        this.action = action;
    }

    @NonNull
    public String title() {
        return title;
    }

    @NonNull
    public View.OnClickListener action() {
        return action;
    }

}
