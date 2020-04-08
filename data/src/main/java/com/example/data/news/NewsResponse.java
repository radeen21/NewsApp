package com.example.data.news;

import com.example.data.BaseResponse;

import java.util.List;

public class NewsResponse extends BaseResponse {

    public List<SourceEntity> sources;

    public int totalResults;

    public List<ArticleEntity> articles;

}
