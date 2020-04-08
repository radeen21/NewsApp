package com.example.data.news.repository.source;

import com.example.data.AbstractEntityDataFactory;
import com.example.data.news.repository.source.network.NetworkNewsEntityData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NewsEntityDataFactory extends AbstractEntityDataFactory<NewsEntityData> {

    @Inject
    public NewsEntityDataFactory() {

    }

    @Override
    public NewsEntityData createData() {
        return new NetworkNewsEntityData();
    }
}
