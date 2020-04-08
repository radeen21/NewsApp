package com.example.newsapp.source;

import com.example.domain.news.Source;
import com.example.domain.news.interactor.GetSourcesUseCase;
import com.example.newsapp.base.BasePresenter;
import com.example.newsapp.model.SourceModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class SourcePresenter extends BasePresenter<SourceContract.View>
        implements SourceContract.Presenter {

    private final GetSourcesUseCase getSourcesUseCase;
    private final SourceModelMapper sourceModelMapper;

    @Inject
    public SourcePresenter(GetSourcesUseCase getSourcesUseCase, SourceModelMapper sourceModelMapper) {
        this.getSourcesUseCase = getSourcesUseCase;
        this.sourceModelMapper = sourceModelMapper;
    }

    @Override
    public void loadNewsSources() {
        getView().showProgressBar();

        getSourcesUseCase.execute(new DisposableObserver<List<Source>>() {
            @Override
            public void onNext(List<Source> sources) {
                getView().hideProgressBar();
                getView().onNewsSourcesLoaded(sourceModelMapper.transform(sources));
            }

            @Override
            public void onError(Throwable e) {
                getView().hideProgressBar();
            }

            @Override
            public void onComplete() {
                getView().hideProgressBar();
            }
        });
    }

    @Override
    protected void onDestroy() {
        getSourcesUseCase.clearAllDisposable();
    }
}
