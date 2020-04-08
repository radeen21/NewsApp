package com.example.newsapp.model;

import com.example.domain.news.Source;
import com.example.newsapp.di.PerActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class SourceModelMapper {

    @Inject
    public SourceModelMapper() {

    }

    public List<SourceModel> transform(List<Source> sources) {
        ArrayList<SourceModel> sourceModels = new ArrayList<>();

        if (sources != null && !sources.isEmpty()) {
            for (Source source : sources) {
                SourceModel sourceModel = new SourceModel();

                sourceModel.id = source.id;
                sourceModel.name = source.name;
                sourceModel.description = source.description;
                sourceModel.url = source.url;
                sourceModel.category = source.category;
                sourceModel.language = source.language;
                sourceModel.us = source.us;

                sourceModels.add(sourceModel);
            }
        }

        return sourceModels;
    }
}
