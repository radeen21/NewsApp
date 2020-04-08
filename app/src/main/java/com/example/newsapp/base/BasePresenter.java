package com.example.newsapp.base;

public abstract class BasePresenter<T extends BaseContractView> {

    private T view;

    public void setView(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    protected abstract void onDestroy();
}
