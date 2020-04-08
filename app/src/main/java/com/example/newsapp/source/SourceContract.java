package com.example.newsapp.source;

import com.example.newsapp.base.BaseContractView;
import com.example.newsapp.model.SourceModel;

import java.util.List;

public interface SourceContract {

    interface View extends BaseContractView {
        void onNewsSourcesLoaded(List<SourceModel> sourceModelList);
    }

    interface Presenter {
        void loadNewsSources();
    }
}
