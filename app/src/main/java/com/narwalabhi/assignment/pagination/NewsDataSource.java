package com.narwalabhi.assignment.pagination;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.narwalabhi.assignment.models.ArticlesItem;
import com.narwalabhi.assignment.models.NewsApiResponse;
import com.narwalabhi.assignment.network.APIService;
import com.narwalabhi.assignment.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDataSource extends PageKeyedDataSource<Integer, ArticlesItem> {

    private static final String TAG = NewsDataSource.class.getSimpleName();
    private final String newsSource;
    APIService apiService;
    private final int PAGE = 1;

    public NewsDataSource(String newsSource){
        this.newsSource = newsSource;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ArticlesItem> callback) {
        apiService = RetrofitClient.getService();
        Call<NewsApiResponse> apiResponseCall = apiService.getNews(newsSource, "4e948934687e4744a984a9aa35d022ef", PAGE, params.requestedLoadSize);
        apiResponseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.body());
                    List<ArticlesItem> articlesItems = response.body().getArticles();
                    callback.onResult(articlesItems, null, PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticlesItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticlesItem> callback) {
        apiService = RetrofitClient.getService();
        Call<NewsApiResponse> apiResponseCall = apiService.getNews(newsSource, "4e948934687e4744a984a9aa35d022ef", params.key, params.requestedLoadSize);
        apiResponseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if(response.isSuccessful()){
                    List<ArticlesItem> articlesItems = response.body().getArticles();
                    callback.onResult(articlesItems,params.key + 1);
                    Log.d(TAG, "onResponse: loadAfter" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

            }
        });
    }
}
