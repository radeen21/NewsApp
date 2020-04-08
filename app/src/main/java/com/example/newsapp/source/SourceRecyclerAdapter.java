package com.example.newsapp.source;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.base.BaseRecyclerAdapter;
import com.example.newsapp.model.SourceModel;

public class SourceRecyclerAdapter extends BaseRecyclerAdapter<SourceModel> {

    public SourceRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getHeaderLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.adapter_source;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderHolder(View view) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemHolder(View view) {
        return new SourceHolder(view);
    }

    @Override
    protected void processHeaderHolder(RecyclerView.ViewHolder holder) {

    }

    @Override
    protected void processItemHolder(RecyclerView.ViewHolder holder, SourceModel sourceModel, int position) {
        SourceHolder sourceHolder = (SourceHolder) holder;

        sourceHolder.tvName.setText(sourceModel.name);
        sourceHolder.tvCategory.setText(sourceModel.category);
    }

    class SourceHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvCategory;

        public SourceHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
