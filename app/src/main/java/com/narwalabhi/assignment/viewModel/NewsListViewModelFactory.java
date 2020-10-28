package com.narwalabhi.assignment.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewsListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private String sourceID;

    public NewsListViewModelFactory(Context context, String sourceID){
        this.sourceID = sourceID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsListViewModel(sourceID);
    }
}
