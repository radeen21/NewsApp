package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.newsapp.base.BaseActivity;

public class WebviewActivity extends BaseActivity implements WebViewListener {

    private WebView webView;
    private String url;
    private ProgressBar progressBar;

    public static void open(Context context, String url) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("url", url);

        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setup() {
        url = getIntent().getStringExtra("url");

        getSupportActionBar().hide();

        webView = findViewById(R.id.webView);
        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebViewClient(this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyChromeClient(this));

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
    }

    @Override
    protected void bindPresenterToView() {

    }

    @Override
    public void onPageStarted(String url) {
        progressBar.setVisibility(View.VISIBLE);
        this.url = url;
    }

    @Override
    public void onPageFinished() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProgressChanged(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }

        super.onBackPressed();
    }
}
