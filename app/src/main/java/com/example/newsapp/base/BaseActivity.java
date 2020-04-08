package com.example.newsapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.newsapp.NewsApplication;
import com.example.newsapp.R;
import com.example.newsapp.di.component.ApplicationComponent;
import com.example.newsapp.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    protected FrameLayout flBody;
    protected Toolbar toolbar;

    protected abstract int getLayout();
    protected abstract void setup();
    protected abstract void bindPresenterToView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        getApplicationComponent().inject(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flBody = findViewById(R.id.flBody);
        View view = getLayoutInflater().inflate(getLayout(), null, true);
        flBody.addView(view);

        setup();
        bindPresenterToView();
    }

    public ApplicationComponent getApplicationComponent() {
        return ((NewsApplication) getApplication()).getApplicationComponent();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
