package com.example;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 2016-06-28.
 */
public class StringConvertTest {

    public interface IZTOService {
        @GET("{path}")
        Call<String> query(@Path("path") String path);
    }

    public static void main(String... args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zto.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        IZTOService badduService = retrofit.create(IZTOService.class);

        Call<String> queryCall = badduService.query("/app/Android/zto_update.xml");
        String responseStr = queryCall.execute().body();
        System.out.println("response:" + responseStr);
    }
}
