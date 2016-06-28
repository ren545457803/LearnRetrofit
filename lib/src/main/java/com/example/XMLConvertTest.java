package com.example;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 2016-06-28.
 */
public class XMLConvertTest {

    public static class UpdateLog {
        public final String versionCode;
        public final String versionName;

        public UpdateLog(String versionCode, String versionName) {
            this.versionCode = versionCode;
            this.versionName = versionName;
        }
    }

    public interface IZTOService {
        @GET("{path}")
        Call<UpdateLog> query(@Path("path") String path);
    }

    public static void main(String... args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zto.com")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        IZTOService badduService = retrofit.create(IZTOService.class);

        Call<UpdateLog> queryCall = badduService.query("/app/Android/zto_update.xml");
        UpdateLog responseBean = queryCall.execute().body();
        System.out.println("response:" + responseBean.versionCode + ":" + responseBean.versionName);
    }
}
