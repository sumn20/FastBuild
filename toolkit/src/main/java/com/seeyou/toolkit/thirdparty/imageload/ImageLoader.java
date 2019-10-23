package com.seeyou.toolkit.thirdparty.imageload;

import android.net.Uri;
import android.widget.ImageView;


/**
 * 对图片加载第三方库进行隔离
 *
 * @author sumn
 * date 2018/8/15
 */
public interface ImageLoader {

    /**
     * 展示图片，同时参数resId可以设置默认的图片
     *
     * @param imageUrl  图片网络地址
     * @param imageView 控件
     * @param resId     defaultImage
     */
    void displayImage(String imageUrl, ImageView imageView, int resId);

    /**
     * 展示图片,无需设置默认占位图
     * @param imageUrl 图片网络地址
     * @param imageView 控件
     */
    void displayImage(String imageUrl, ImageView imageView);

    /**
     * 展示图片，同时参数resId可以设置默认的图片
     *
     * @param imageUrl  图片本地地址
     * @param imageView 控件
     * @param resId     defaultImage
     */
    void displayImage(Uri imageUrl, ImageView imageView, int resId);

     void displayImage(int imageid, ImageView imageView, int resId);



    /**
     * 展示git图片
     *
     * @param imageUrl  图片网络地址
     * @param imageView 控件
     */
    void displayGif(String imageUrl, ImageView imageView);

    /**
     * 展示图片，同时参数ImageLoadingListener可以设置对图片加载过程的监听
     * @param imageUrl
     * @param imageView
     * @param resId
     * @param loadingListener
     */
    // void displayImage(String imageUrl, ImageView imageView, int resId, ImageLoadingListener loadingListener);

    /**
     * 清除缓存
     *
     * @param imageUrl
     */
    void clearCache(String imageUrl);
}
