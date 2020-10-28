package com.narwalabhi.assignment.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit client;
    private static final String BaseUrl = "https://newsapi.org/v2/";
    public static Retrofit getRetrofitClient(){
        if(client == null){
            client = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return client;
    }
    public static APIService getService() {
        return getRetrofitClient().create(APIService.class);
    }
}
