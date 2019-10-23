package com.seeyou.toolkit;

import com.seeyou.toolkit.constants.ToolKitConfiguration;
import com.seeyou.toolkit.thirdparty.imageload.GlideImageLoader;
import com.seeyou.toolkit.thirdparty.imageload.ImageLoader;
import com.seeyou.toolkit.thirdparty.jsonparse.GsonParse;
import com.seeyou.toolkit.thirdparty.jsonparse.IJsonParse;

/**
 * @author sumn
 * date 2019/10/21
 */
public class ToolKit {
    private static class SingletonHolder {
        private static final ToolKit INSTANCE = new ToolKit();
    }

    private ToolKit() {
    }

    public static ToolKit getInstance() {
        return ToolKit.SingletonHolder.INSTANCE;
    }

    private ToolKitConfiguration toolKitConfiguration;
    private ImageLoader imageLoader;
    private IJsonParse iJsonParse;

    public void setToolKitConfiguration(ToolKitConfiguration toolKitConfiguration) {
        this.toolKitConfiguration = toolKitConfiguration;
    }

    public ToolKitConfiguration getToolKitConfiguration() {
        if (toolKitConfiguration == null) {
            throw new NullPointerException(" need configuration ToolKit!");
        }
        return toolKitConfiguration;
    }

    public ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = new GlideImageLoader(getToolKitConfiguration().getContext());
        }
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public IJsonParse getiJsonParse() {
        if (iJsonParse == null) {
            iJsonParse = new GsonParse();
        }
        return iJsonParse;
    }

    public void setiJsonParse(IJsonParse iJsonParse) {
        this.iJsonParse = iJsonParse;
    }
}
