package com.narwalabhi.assignment.pagination;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class NewsDataSourceFactory extends DataSource.Factory {

    NewsDataSource newsDataSource;
    MutableLiveData<NewsDataSource> mutableLiveData;
    String newsSource;

    public NewsDataSourceFactory(String newsSource) {
        this.newsSource = newsSource;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        newsDataSource = new NewsDataSource(newsSource);
        mutableLiveData.postValue(newsDataSource);
        Log.d("Main", "create: " + mutableLiveData.getValue());
        return newsDataSource;
    }

    public MutableLiveData<NewsDataSource> getMutableLiveData(){
        return mutableLiveData;
    }

}
