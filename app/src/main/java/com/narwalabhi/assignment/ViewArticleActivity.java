package com.narwalabhi.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.narwalabhi.assignment.ui.BlankFragment;

public class ViewArticleActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        webView = findViewById(R.id.webView);
        if(savedInstanceState != null){
            webView.restoreState(savedInstanceState);
        }else{
            webView.setWebViewClient(new myWebClient());
            webView.getSettings().setJavaScriptEnabled(true);
            Intent intent = getIntent();
            if(intent != null && !intent.getExtras().isEmpty()){
                String articleUrl = intent.getStringExtra(BlankFragment.ARTICLE_URL_KEY);
                webView.loadUrl(articleUrl);
            }
        }
    }
    public static class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
             super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
        webView.goBack();
        return true;
    }
        return super.onKeyDown(keyCode, event);
    }
}