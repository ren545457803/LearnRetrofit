package com.example.admin.learnretrofit;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2016-06-28.
 */
public class RxRetrofitTest {
    private static final String TAG = "RxRetrofitTest";

    public interface IZTOService {
        @GET("{path}")
        Observable<String> queryGet(@Path("path") String path);
    }

    public void linkNet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zto.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        IZTOService ztoService = retrofit.create(IZTOService.class);
        Observable<String> queryCaller = ztoService.queryGet("app/Android/zto_update.xml");

        Log.d(TAG, "开始Rx连网");
        queryCaller.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, Thread.currentThread() + ":结果：");
            }
        });
    }
}
