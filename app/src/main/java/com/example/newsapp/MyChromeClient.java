package com.example.newsapp;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyChromeClient extends WebChromeClient {

    private WebViewListener listener;

    public MyChromeClient(WebViewListener webViewListener) {
        listener = webViewListener;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);

        listener.onProgressChanged(newProgress);
    }
}
