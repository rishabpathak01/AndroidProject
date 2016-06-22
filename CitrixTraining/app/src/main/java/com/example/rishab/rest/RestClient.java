package com.example.rishab.rest;
import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.entity.StringEntity;
/**
 * Created by Rishab on 6/19/2016.
 */
public class RestClient
    {
        private static final String BASE_URL = "http://192.168.0.10:8080/training/api/user/";
       private static AsyncHttpClient client = new AsyncHttpClient();
        public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
         {
             client.addHeader("Accept","application/json");
             client.get(getAbsoluteUrl(url), params, responseHandler);
         }

    public static void post(Context context, String url, RequestParams params, StringEntity entity, AsyncHttpResponseHandler responseHandler)
    {

        client.addHeader("Accept","application/json");
        client.post(context,getAbsoluteUrl(url), entity, "application/json",
                responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
