package com.narwalabhi.assignment.network;

import com.narwalabhi.assignment.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {


    @GET("top-headlines")
    Call<NewsApiResponse> getNews(@Query("sources") String source,
                                  @Query("apiKey") String apiKey,
                                  @Query("page") long page,
                                  @Query("pageSize") int pageSize);

}
