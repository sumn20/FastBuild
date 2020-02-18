package com.seeyou.toolkit.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

public class FileRequest<T extends BaseModel> extends Request<T> {
    private final Gson gson = new Gson();
    public static final String CONNECT_EXCEPTION = "网络连接异常";//，请检查您的网络状态
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时";//，请检查您的网络状态，稍后重试
    public static final String MALFORMED_JSON_EXCEPTION = "数据解析错误";
    public static final String REQUEST_ERROR = "请求错误";

    private static final String CHARSET = "utf-8"; //设置编码
    String BOUNDARY = UUID.randomUUID().toString();  //边界标识   随机生成
    String PREFIX = "--", LINE_END = "\r\n";
    String CONTENT_TYPE = "multipart/form-data";   //内容类型

    @Nullable
    @GuardedBy("mLock")
    private Response.Listener<T> mListener;
    private Class<T> clazz;
    /*请求 数据通过参数的形式传入*/
    private List<FormImage> mListItem;

    public FileRequest(String url,  List<FormImage> listItem, Class<T> clazz, Response.Listener<T> mListener, @Nullable Response.ErrorListener listener) {
        super(Method.POST, url, listener);
        this.mListener = mListener;
        this.clazz = clazz;
        this.mListItem = listItem;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
    public FileRequest(String url,FormImage formImage, Class<T> clazz, Response.Listener<T> mListener, @Nullable Response.ErrorListener listener) {
        super(Method.POST, url, listener);
        this.mListener = mListener;
        this.clazz = clazz;
        this.mListItem = new ArrayList<>();
        mListItem.add(formImage);
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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



    @Override
    public byte[] getBody() throws AuthFailureError {
        if (mListItem == null||mListItem.size() == 0){
            return super.getBody() ;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
        int N = mListItem.size() ;
        FormImage formImage ;
        for (int i = 0; i < N ;i++){
            formImage = mListItem.get(i) ;
            StringBuffer sb = new StringBuffer();
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_END);
            //    相关参数需与后台一致
//     sb.append("Content-Disposition: form-image; name=\"file\"; filename=\"" + formImage.getFileName() + "\"" + LINE_END);
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + formImage.getFileName() + "\"" + LINE_END);
            sb.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINE_END);
            sb.append(LINE_END);
            try {
                bos.write(sb.toString().getBytes(CHARSET));
                bos.write(formImage.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            bos.write(LINE_END.getBytes(CHARSET));
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes(CHARSET);
            bos.write(end_data);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
    @Override
    public String getBodyContentType() {
        return CONTENT_TYPE + ";boundary=" + BOUNDARY;
    }

    private HashMap<String,String> map;

    public void setHeader(HashMap<String,String> map){
        this.map = map;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if(null != map){
            return map;
        }
        return super.getHeaders();
    }
}
