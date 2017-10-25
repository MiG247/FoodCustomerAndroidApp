package runninglight.goghox.com.testmodule;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by GogHox on 2017/10/11.
 */

public class Net {
    private static Net instance;
    private Net(){}
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient okHttpClient = new OkHttpClient();

    public static Net getInstance(){
        if(Net.instance == null){
            instance = new Net();
        }
        return instance;
    }
    Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    public void get(String url, Callback callback){
        //1. get OkHttpClient
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient();
        }

        //2.创建Request.Builder对象，设置参数，请求方式如果是Get，就不用设置，默认就是Get
        // Create Request.Builder
        Request request = new Request.Builder()
                .url(url)
                .build();

        //3.创建一个Call对象，参数是request对象，发送请求
        Call call = okHttpClient.newCall(request);

        //4.异步请求，请求加入调度
        call.enqueue(callback);
    }
}
