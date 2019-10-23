package com.seeyou.toolkit.network;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.seeyou.toolkit.base.BaseModel;
import com.seeyou.toolkit.utils.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author sumn
 * date 2019/10/21
 */
public class CustomRequest<T extends BaseModel> extends Request<T> {

    private final Gson gson = new Gson();
    public static final String CONNECT_EXCEPTION = "网络连接异常";//，请检查您的网络状态
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时";//，请检查您的网络状态，稍后重试
    public static final String MALFORMED_JSON_EXCEPTION = "数据解析错误";
    public static final String REQUEST_ERROR = "请求错误";


    @Nullable
    @GuardedBy("mLock")
    private Response.Listener<T> mListener;
    private Class<T> clazz;


    public CustomRequest(int method, String url,  Class<T> clazz, Response.Listener<T> mListener, @Nullable Response.ErrorListener listener) {
        super(method, url, listener);
        this.mListener = mListener;
        this.clazz = clazz;

    }

    public CustomRequest(String url,Class<T> clazz, Response.Listener<T> mListener, @Nullable Response.ErrorListener listener) {
        this(Method.GET, url, clazz, mListener, listener);
    }



    @Override
    public void deliverError(VolleyError error) {
        Throwable throwable = error.getCause();
        if (throwable instanceof SocketTimeoutException) {
            ToastUtil.showShort(SOCKET_TIMEOUT_EXCEPTION);
        } else if (throwable instanceof ConnectException) {
            ToastUtil.showShort(CONNECT_EXCEPTION);
        } else if (throwable instanceof UnknownHostException) {
            ToastUtil.showShort(CONNECT_EXCEPTION);
        } else if (throwable instanceof MalformedJsonException) {
            ToastUtil.showShort(MALFORMED_JSON_EXCEPTION);
        } else {
            ToastUtil.showShort(REQUEST_ERROR);
        }
        super.deliverError(error);


    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }


}
