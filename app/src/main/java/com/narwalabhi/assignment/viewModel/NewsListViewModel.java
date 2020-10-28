package com.narwalabhi.assignment.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.narwalabhi.assignment.models.ArticlesItem;
import com.narwalabhi.assignment.pagination.NewsDataSource;
import com.narwalabhi.assignment.pagination.NewsDataSourceFactory;

public class NewsListViewModel extends ViewModel {

    private static final String TAG = NewsListViewModel.class.getSimpleName();
    private final int PAGE_SIZE = 10;
    NewsDataSourceFactory newsDataSourceFactory;
    MutableLiveData<NewsDataSource> dataSourceMutableLiveData;
    LiveData<PagedList<ArticlesItem>> pagedListLiveData;

    public NewsListViewModel(String sourceId) {
        newsDataSourceFactory = new NewsDataSourceFactory(sourceId);
        dataSourceMutableLiveData = newsDataSourceFactory.getMutableLiveData();
        Log.d(TAG, "MainActivityViewModel: " + newsDataSourceFactory);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE+5)
                .setPageSize(PAGE_SIZE)
                .build();
        pagedListLiveData = new LivePagedListBuilder<Integer, ArticlesItem>(newsDataSourceFactory, config)
                .build();
    }

    public LiveData<PagedList<ArticlesItem>> getPagedListLiveData(){
        return pagedListLiveData;
    }

}
