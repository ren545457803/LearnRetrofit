package com.example;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by admin on 2016-06-28.
 */
public class RxConvertTest {
    public interface IZTOService{
        @GET("{path}")
        Observable<String> queryGet(@Path("path") String path);
    }

    public static void main(String... args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zto.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        IZTOService ztoService = retrofit.create(IZTOService.class);
        Observable<String> queryCaller = ztoService.queryGet("app/Android/zto_update.xml");

        queryCaller.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.printf("response-Observable:"+s);
            }
        });


    }
}
