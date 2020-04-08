package com.example.newsapp;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    private WebViewListener listener;

    public MyWebViewClient(WebViewListener webViewListener) {
        listener = webViewListener;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        listener.onPageStarted(url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        listener.onPageFinished();
        listener.onProgressChanged(100);
    }
}
