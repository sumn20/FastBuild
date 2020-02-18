package com.seeyou.toolkit.network;

import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.seeyou.toolkit.ToolKit;
import com.seeyou.toolkit.base.BaseModel;
import com.seeyou.toolkit.intfic.NetWorkCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sumn
 * date 2019/10/21
 */
public class NetworkUtils {

    private static class SingletonHolder {
        private static final NetworkUtils INSTANCE = new NetworkUtils();
    }

    private NetworkUtils() {
        queue = Volley.newRequestQueue(ToolKit.getInstance().getToolKitConfiguration().getContext());
    }

    public static NetworkUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RequestQueue queue;

    public <T extends BaseModel> void doGet(String requestValue, Map<String, String> params, Class<T> tClass, NetWorkCallback<T> callback) {
        callback.Loading();
        String url = buildQueryUrl(ToolKit.getInstance().getToolKitConfiguration().getBaseUrl() + requestValue, params);
        CustomRequest<T> customRequest = new CustomRequest<>(url, tClass,
                callback::Success, error -> callback.Error(error.getLocalizedMessage()));
        customRequest.setTag(requestValue);
        queue.add(customRequest);
    }

    public <T extends BaseModel> void doPost(String requestValue, Map<String, String> params, Class<T> tClass, NetWorkCallback<T> callback) {
        callback.Loading();
        String url = buildQueryUrl(ToolKit.getInstance().getToolKitConfiguration().getBaseUrl() + requestValue, params);
        CustomRequest<T> customRequest = new CustomRequest<>(Request.Method.POST, url, tClass,
                callback::Success, error -> callback.Error(error.getLocalizedMessage()));

        customRequest.setTag(requestValue);
        queue.add(customRequest);
    }

    public <T extends BaseModel> void doOther(int method, String requestValue, Map<String, String> params, Class<T> tClass, NetWorkCallback<T> callback) {
        callback.Loading();
        String url = buildQueryUrl(ToolKit.getInstance().getToolKitConfiguration().getBaseUrl() + requestValue, params);
        CustomRequest<T> customRequest = new CustomRequest<>(method, url, tClass,
                callback::Success, error -> callback.Error(error.getLocalizedMessage()));

        customRequest.setTag(requestValue);
        queue.add(customRequest);
    }

    public <T extends BaseModel> void fileListUpload(String requestValue, List<FormImage> listItem, Class<T> clazz, NetWorkCallback<T> callback) {
        callback.Loading();
        FileRequest<T> fileRequest = new FileRequest<T>(ToolKit.getInstance().getToolKitConfiguration().getBaseUrl() + requestValue, listItem, clazz, callback::Success, error -> callback.Error(error.getLocalizedMessage()));
        fileRequest.setTag(requestValue);
        queue.add(fileRequest);
    }

    public <T extends BaseModel> void fileUpload(String requestValue, FormImage formImage, Class<T> clazz, NetWorkCallback<T> callback) {
        List<FormImage> formImages = new ArrayList<>();
        formImages.add(formImage);
        fileListUpload(requestValue, formImages, clazz, callback);
    }

    private String buildQueryUrl(String uri, Map<String, String> params) {
        StringBuilder queryBuilder = new StringBuilder(uri);
        if (!uri.contains("?")) {
            queryBuilder.append("?");
        } else if (!uri.endsWith("?")) {
            queryBuilder.append("&");
        }
        if (params != null) {
            for (String key : params.keySet()) {
                String name = key;
                String value = params.get(key);
                if (!TextUtils.isEmpty(name) && value != null) {
                    queryBuilder.append(
                            Uri.encode(name, "UTF-8"))
                            .append("=")
                            .append(Uri.encode(value, "UTF-8"))
                            .append("&");
                }
            }
        }

        if (queryBuilder.charAt(queryBuilder.length() - 1) == '&') {
            queryBuilder.deleteCharAt(queryBuilder.length() - 1);
        }

        if (queryBuilder.charAt(queryBuilder.length() - 1) == '?') {
            queryBuilder.deleteCharAt(queryBuilder.length() - 1);
        }
        return queryBuilder.toString();
    }

    public void cancel(String tag) {
        queue.cancelAll(tag);
    }
}
