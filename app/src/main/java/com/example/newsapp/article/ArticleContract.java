package com.example.newsapp.article;


import com.example.newsapp.base.BaseContractView;
import com.example.newsapp.model.ArticleModel;

import java.util.List;

public interface ArticleContract {

    interface View extends BaseContractView {
        void onArticleLoaded(List<ArticleModel> articleModels);
        void clearAdapter();
    }

    interface Presenter {
        void loadArticle(String source, int size);
        void searchArticle(String q, int size);
    }
}
