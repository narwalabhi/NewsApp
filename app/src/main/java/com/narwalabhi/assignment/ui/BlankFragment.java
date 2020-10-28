package com.narwalabhi.assignment.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.narwalabhi.assignment.MainActivity;
import com.narwalabhi.assignment.R;
import com.narwalabhi.assignment.ViewArticleActivity;
import com.narwalabhi.assignment.adapters.ArticlesAdapter;
import com.narwalabhi.assignment.models.ArticlesItem;
import com.narwalabhi.assignment.viewModel.NewsListViewModel;
import com.narwalabhi.assignment.viewModel.NewsListViewModelFactory;


public class BlankFragment extends Fragment implements ArticlesAdapter.OnItemClickListener {

    private static final String ARG_PARAM1 = "sourceId";
    public static final String ARTICLE_URL_KEY = "article_url";
    private static final String TAG = BlankFragment.class.getSimpleName();
    private NewsListViewModel newsListViewModel;
    private RecyclerView rvArticles;
    private String sourceID;
    private ArticlesAdapter adapter;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sourceID = getArguments().getString(ARG_PARAM1);
        }
        NewsListViewModelFactory newsListViewModelFactory = new NewsListViewModelFactory(requireContext(), sourceID);
        newsListViewModel = ViewModelProviders.of(this, newsListViewModelFactory).get(NewsListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        rvArticles = v.findViewById(R.id.rvArticles);
        rvArticles.setLayoutManager(new LinearLayoutManager(container.getContext()));
        adapter = new ArticlesAdapter();
        rvArticles.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newsListViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<ArticlesItem>>() {
            @Override
            public void onChanged(PagedList<ArticlesItem> articlesItems) {
                adapter.submitList(articlesItems);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: " + position);
        ArticlesItem articlesItem = newsListViewModel.getPagedListLiveData().getValue().get(position);
        Intent intent = new Intent(requireContext(), ViewArticleActivity.class);
        intent.putExtra(ARTICLE_URL_KEY, articlesItem.getUrl());
        startActivity(intent);
    }
}