package com.example.domain;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<T, Params> {

    private CompositeDisposable compositeDisposable;

    protected abstract Observable<T> buildUseCaseObservable(Params params);

    protected UseCase() {
        initCompositeDisposable();
    }

    private void initCompositeDisposable() {
        compositeDisposable = new CompositeDisposable();
    }

    private void addDisposable(Disposable disposable) {
        if (compositeDisposable != null && compositeDisposable.isDisposed()) {
            initCompositeDisposable();
        }

        if (disposable != null && !disposable.isDisposed()) {
            compositeDisposable.remove(disposable);
        }

        if (compositeDisposable != null && disposable != null) {
            compositeDisposable.add(disposable);
        }
    }

    public void clearAllDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void execute(DisposableObserver<T> disposableObserver) {
        execute(disposableObserver, null);
    }

    public void execute(DisposableObserver<T> disposableObserver, Params params) {
        addDisposable(buildUseCaseObservable(params).subscribeWith(disposableObserver));
    }
}
