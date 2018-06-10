package com.SillyGoose.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static android.content.ContentValues.TAG;

/**
 * OkHttp
 * Connect to Server and POST\GET Message
 * Signle Model
 * Created by wangj on 2018/4/19.
 */

public class OkHttpUnits {
    /**
     * 此类单例，防止多次连接等问题
     */
    private static OkHttpUnits httpUnits=null;
    private static MediaType JSON=MediaType.parse("application/json");
    private static String url="http://172.25.32.244:8080";

    private OkHttpUnits(){
    }
    public static OkHttpUnits getInstance(){
        if(httpUnits == null){
            synchronized (OkHttpUnits.class){
                httpUnits=new OkHttpUnits();
            }
        }
        return httpUnits;
    }
    /*
     * initialize OkHttpClient
     */
    private static OkHttpClient client = null;
    public synchronized static OkHttpClient getClient(){
        if(client == null){
            /* Use builder.build() Initialize client */

            // Cache Dir
            File sdcache=new File(Environment.getExternalStorageDirectory(),"SillyGooseClientCache");
            int cacheSize=10*1024*1024;//10M

            /* Set Intercepter */
            HttpLoggingInterceptor httpLoggingInterceptor;
            httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message){
                    Log.i("Client",message.toString());
                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            /* Initialize */
            client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    //添加OkHttp3的拦截器
                    .addInterceptor(httpLoggingInterceptor)
                    .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
                    .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                    .build();
        }
        return client;
    }
    private static Handler mHandler = null;

    /**
     * Handler类，可惜没有用
     * @return
     */
    public synchronized static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }

        return mHandler;
    }


    /**
     * 这里由于url域名部分不变，所以如此设置
     * @param params url 后缀(域名/后部分)
     * @return  拼接后url；
     */
    public static String setAndGetUrl(String params){
        return url+params;
    }


    /**
     * Async Get Method
     * @param url
     * @param callback
     * @return
     */
    public static MessageBox get(String url, Callback callback){

        MessageBox messageBox = null;
        OkHttpClient client=getClient();
        Request request = new Request.Builder().url(url).build();
        // asynchronous
        Call call = client.newCall(request);
        call.enqueue(callback);

        return messageBox;
    }

    /**
     * Sync Get Method
     * @param url
     * @return
     */
    public static MessageBox get(String url){
        MessageBox messageBox = null;

        OkHttpClient client=getClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try{
            Response response=call.execute();
            messageBox = MessageBox.valueOf(response.body().toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        return messageBox;
    }

    /**
     * Sync Post Method
     * @param url
     * @param params
     * @return
     */
    public static MessageBox post(String url, JSONObject params) throws IOException, JSONException {
        MessageBox messageBox = null;
        String postmessage = params.toString();
        RequestBody body = RequestBody.create(JSON,postmessage);
        OkHttpClient client = getClient();
        JSONObject jsonObject = null;

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        String msg=response.body().string();
        jsonObject = new JSONObject(msg);
        Log.d(TAG,"msg is" + msg);
        if(response.isSuccessful()){
            messageBox = MessageBox.valueOf(jsonObject.getString("Result"));
        }else{
            messageBox = MessageBox.SYS_NETERR;
        }
        return messageBox;
    }

    /**
     * Async Post
     * @param url
     * @param data
     * @param call
     * @return
     * @throws IOException
     */
    public static MessageBox post(String url, JSONObject data,Callback call) throws IOException{
        MessageBox messageBox = null;
        String postmessage = data.toString();

        RequestBody body = RequestBody.create(JSON,postmessage);
        OkHttpClient client = getClient();
        /* 请求 */
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        /* 响应 */
        client.newCall(request).enqueue(call);


        return messageBox;
    }

    /**
     * Sync Post method but return JSON data
     * @param url
     * @param params
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject postForGetJSON(String url, JSONObject params) throws IOException, JSONException {
        MessageBox messageBox = null;
        String postmessage = params.toString();
        RequestBody body = RequestBody.create(JSON,postmessage);
        OkHttpClient client = getClient();
        JSONObject jsonObject = null;

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        String msg=response.body().string();
        jsonObject = new JSONObject(msg);
        Log.d(TAG,"msg is" + msg);
        return jsonObject;
    }

    /**
     * get bitmap from service
     * @param userId get Pic User
     * @param picLevel pic level
     * @param picBelong pic belong
     * @return
     * @throws IOException
     */
    public static Bitmap getPic(String userId, String picLevel, String picBelong) throws IOException {
        Bitmap bitmap;
        OkHttpClient client = getClient();
        LinkedHashMap<String,String> params = new LinkedHashMap<String,String>();
        params.put("userId",userId);
        params.put("picLevel",picLevel);
        params.put("picBelong",picBelong);
        String url = attachHttpGetParams(setAndGetUrl("/get/download"),params);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        bitmap = BitmapFactory.decodeStream(response.body().byteStream());
        return bitmap;
    }
    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, LinkedHashMap<String,String> params){

        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i=0;i<params.size();i++ ) {
            String value=null;
            try {
                value= URLEncoder.encode(values.next(),"utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            stringBuffer.append(keys.next()+"="+value);
            if (i!=params.size()-1) {
                stringBuffer.append("&");
            }
        }

        return url + stringBuffer.toString();
    }
}
