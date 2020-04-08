package com.example.newsapp.article;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.newsapp.R;
import com.example.newsapp.WebviewActivity;
import com.example.newsapp.base.BaseActivity;
import com.example.newsapp.base.RecyclerViewAdapterListener;
import com.example.newsapp.di.component.DaggerNewsComponent;
import com.example.newsapp.di.component.NewsComponent;
import com.example.newsapp.di.module.NewsModule;
import com.example.newsapp.model.ArticleModel;

import java.util.List;

import javax.inject.Inject;

public class ArticleActivity extends BaseActivity implements ArticleContract.View {

    private static final String sourceIdKey = "source_key";
    private static final String sourceNameKey = "source_name_key";
    private String sourceId;
    private String sourceName;
    private ArticleRecyclerAdapter adapter;
    private ProgressBar progressBar;
    private String q;

    @Inject
    ArticlePresenter articlePresenter;

    private NewsComponent newsComponent;

    public static void open(Context context, String sourceId, String sourceName) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(sourceIdKey, sourceId);
        intent.putExtra(sourceNameKey, sourceName);

        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_article;
    }

    @Override
    protected void setup() {
        initInjector();

        sourceId = getIntent().getStringExtra(sourceIdKey);
        sourceName = getIntent().getStringExtra(sourceNameKey);

        adapter = new ArticleRecyclerAdapter(this);
        adapter.setListener(new RecyclerViewAdapterListener() {
            @Override
            public void onClicked(Object item, int position) {
                ArticleModel articleModel = (ArticleModel) item;

                WebviewActivity.open(ArticleActivity.this, articleModel.url);
            }

            @Override
            public void onLastItem(int size) {
                if (q != null && !q.isEmpty()) {
                    articlePresenter.searchArticle(q, size);
                } else {
                    articlePresenter.loadArticle(sourceId, size);
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        progressBar = findViewById(R.id.progressBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());
        getSupportActionBar().setTitle(sourceName);
    }

    @Override
    protected void bindPresenterToView() {
        articlePresenter.setView(this);
        articlePresenter.loadArticle(sourceId, 0);
    }

    private void initInjector() {
        if (newsComponent == null) {
            newsComponent = DaggerNewsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(getActivityModule())
                    .newsModule(new NewsModule()).build();
        }

        newsComponent.inject(this);
    }

    @Override
    public void onArticleLoaded(List<ArticleModel> articleModels) {
        adapter.add(articleModels);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clearAdapter() {
        adapter.clear();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        articlePresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                articlePresenter.searchArticle(query, 0);
                q = query;
                getSupportActionBar().setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
