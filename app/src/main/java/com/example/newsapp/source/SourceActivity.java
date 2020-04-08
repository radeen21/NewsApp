package com.example.newsapp.source;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;

import com.example.newsapp.R;
import com.example.newsapp.article.ArticleActivity;
import com.example.newsapp.base.BaseActivity;
import com.example.newsapp.base.RecyclerViewAdapterListener;
import com.example.newsapp.di.component.DaggerNewsComponent;
import com.example.newsapp.di.component.NewsComponent;
import com.example.newsapp.di.module.NewsModule;
import com.example.newsapp.model.SourceModel;

import java.util.List;

import javax.inject.Inject;

public class SourceActivity extends BaseActivity implements SourceContract.View {

    private NewsComponent newsComponent;
    private ProgressBar progressBar;
    private SourceRecyclerAdapter adapter;

    @Inject
    SourcePresenter sourcePresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_source;
    }

    @Override
    protected void setup() {
        initInjector();

        adapter = new SourceRecyclerAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        float cardViewWidth = getResources().getDimension(R.dimen.card_item)/density;
        int newSpanCount = (int) Math.floor(dpWidth / cardViewWidth);
        recyclerView.setLayoutManager(new GridLayoutManager(this, newSpanCount));

        recyclerView.setAdapter(adapter);
        adapter.setListener(new RecyclerViewAdapterListener() {
            @Override
            public void onClicked(Object item, int position) {
                SourceModel sourceModel = (SourceModel) item;

                ArticleActivity.open(SourceActivity.this, sourceModel.id, sourceModel.name);
            }

            @Override
            public void onLastItem(int size) {

            }
        });

        progressBar = findViewById(R.id.progressBar);
    }

    private void initInjector() {
        if (newsComponent == null) {
            newsComponent = DaggerNewsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(getActivityModule())
                    .newsModule(new NewsModule())
                    .build();
        }

        newsComponent.inject(this);
    }

    @Override
    protected void bindPresenterToView() {
        sourcePresenter.setView(this);
        sourcePresenter.loadNewsSources();
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
    public void onNewsSourcesLoaded(List<SourceModel> sourceModelList) {
        adapter.clear();
        adapter.add(sourceModelList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        sourcePresenter.onDestroy();
        super.onDestroy();
    }
}
