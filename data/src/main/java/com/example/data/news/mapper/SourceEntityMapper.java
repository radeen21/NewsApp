package com.example.data.news.mapper;


import com.example.data.news.SourceEntity;
import com.example.domain.news.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class SourceEntityMapper {

    @Inject
    public SourceEntityMapper() {

    }

    public List<Source> transform(List<SourceEntity> sourceEntities) {
        ArrayList<Source> sources = new ArrayList<>();

        if (sourceEntities != null && !sourceEntities.isEmpty()) {
            for (SourceEntity entity : sourceEntities) {
                Source source = new Source();

                source.id = entity.id;
                source.name = entity.name;
                source.description = entity.description;
                source.url = entity.url;
                source.category = entity.category;
                source.language = entity.language;
                source.us = entity.us;

                sources.add(source);
            }
        }

        return sources;
    }
}
