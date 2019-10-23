package com.seeyou.toolkit.thirdparty.imageload;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.seeyou.toolkit.R;


/**
 * Glide实现隔离接口
 *
 * @author sumn
 * date 2018/8/15
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;
    private Glide glide;

    public GlideImageLoader(Context context) {
        if (requestManager == null) {
            requestManager = Glide.with(context);
        }
        if (glide == null) {
            glide = Glide.get(context);
        }
    }

    @Override
    public void displayImage(String imageUrl, ImageView imageView, int resId) {
        RequestOptions options = new RequestOptions()
                .placeholder(resId).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestManager.load(imageUrl).apply(options).into(imageView);
    }

    @Override
    public void displayImage(String imageUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestManager.load(imageUrl).apply(options).into(imageView);
    }

    @Override
    public void displayImage(Uri imageUrl, ImageView imageView, int resId) {
        RequestOptions options = new RequestOptions()
                .placeholder(resId).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestManager.load(imageUrl).apply(options).into(imageView);
    }
    @Override
    public void displayImage(int imageid, ImageView imageView, int resId) {
        RequestOptions options = new RequestOptions()
                .placeholder(resId).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestManager.load(imageid).apply(options).into(imageView);
    }

    @Override
    public void displayGif(String imageUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestManager.load(imageUrl).apply(options).into(imageView);
    }


    @Override
    public void clearCache(String imageUrl) {
        if (glide != null) {
            glide.clearDiskCache();
        }
    }
}
