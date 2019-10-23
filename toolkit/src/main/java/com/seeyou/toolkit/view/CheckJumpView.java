package com.seeyou.toolkit.view;

import android.content.Context;
import android.content.res.TypedArray;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.seeyou.toolkit.R;


/**
 * @author sumn  2019/5/30 16:16
 */
public class CheckJumpView extends LinearLayout {
    private TextView jumpTitle;
    private TextView jumpTxt;
    private ImageView jumpImg;
    private View jumpRight;


    public CheckJumpView(Context context) {
        this(context, null);
    }

    public CheckJumpView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CheckJumpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_check_jump_view, this, true);
        jumpTitle = findViewById(R.id.jump_title);
        jumpTxt = findViewById(R.id.jump_txt);
        jumpImg = findViewById(R.id.jump_img);
        jumpRight = findViewById(R.id.jump_right);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckJumpView, defStyleAttr, 0);
        jumpTitle.setText(a.getText(R.styleable.CheckJumpView_jumpTitle));
        jumpImg.setVisibility(a.getBoolean(R.styleable.CheckJumpView_showImage, false) ? VISIBLE : GONE);
        jumpImg.setImageResource(a.getResourceId(R.styleable.CheckJumpView_image, R.drawable.ic_jump_img));
        jumpRight.setVisibility(a.getBoolean(R.styleable.CheckJumpView_showRight, true) ? VISIBLE : GONE);
        CharSequence txt = a.getText(R.styleable.CheckJumpView_jumpText);
        if (TextUtils.isEmpty(txt)) {
            jumpTxt.setVisibility(GONE);
        } else {
            jumpTxt.setVisibility(VISIBLE);
            jumpTxt.setText(txt);
        }
    }

    public void setJumpTitle(String title) {
        jumpTitle.setText(title);
    }

    public void setJumpTxt(String title) {
        if (jumpTxt.getVisibility() == GONE) {
            jumpTxt.setVisibility(VISIBLE);
        }
        jumpTxt.setText(title);
    }

    public String getJumpTxt() {
        return jumpTxt.getText().toString();
    }

    public String getJumpTitle() {
        return jumpTitle.getText().toString();
    }
}
