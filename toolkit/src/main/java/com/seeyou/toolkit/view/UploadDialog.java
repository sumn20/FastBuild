package com.seeyou.toolkit.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.seeyou.toolkit.R;

import androidx.annotation.NonNull;

/**
 * @author xialei
 * date 2019/7/17
 */
public class UploadDialog extends Dialog {
    private View.OnClickListener albumListener;
    private View.OnClickListener takePhotoListener;

    public UploadDialog(@NonNull Context context) {
        super(context, R.style.m_dialog);

    }

    public void setAlbumListener(View.OnClickListener albumListener) {
        this.albumListener = albumListener;
    }

    public void setTakePhotoListener(View.OnClickListener takePhotoListener) {
        this.takePhotoListener = takePhotoListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_upload);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        TextView  album = findViewById(R.id.text_album);
        TextView takePhoto = findViewById(R.id.text_photo);
        setCanceledOnTouchOutside(true);

        album.setOnClickListener(v -> {
            dismiss();
            if (albumListener!=null){
                albumListener.onClick(v);
            }

        });
        takePhoto.setOnClickListener(v -> {
            dismiss();
            if (albumListener!=null){
                takePhotoListener.onClick(v);
            }


        });
    }
}
