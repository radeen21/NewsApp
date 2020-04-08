package com.example.newsapp.model;

import com.example.domain.news.Article;
import com.example.newsapp.di.PerActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class ArticleModelMapper {

    @Inject
    public ArticleModelMapper() {

    }

    public List<ArticleModel> transform(List<Article> articles) {
        ArrayList<ArticleModel> articleModels = new ArrayList<>();

        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
                ArticleModel articleModel = new ArticleModel();

                SourceModel sourceModel = new SourceModel();
                sourceModel.id = article.source.id;
                sourceModel.name = article.source.name;

                articleModel.source = sourceModel;

                articleModel.source.id = article.source.id;
                articleModel.source.name = article.source.name;
                articleModel.author = article.author;
                articleModel.title = article.title;
                articleModel.description = article.description;
                articleModel.url = article.url;
                articleModel.urlToImage = article.urlToImage;
                articleModel.publishedAt = article.publishedAt;

                articleModels.add(articleModel);
            }
        }

        return articleModels;
    }
}
