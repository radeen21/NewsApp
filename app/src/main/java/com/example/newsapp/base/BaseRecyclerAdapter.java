package com.example.newsapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;

    protected Context context;
    protected List<T> list;
    protected RecyclerViewAdapterListener listener;

    protected abstract int getHeaderLayout();
    protected abstract int getItemLayout();

    protected abstract RecyclerView.ViewHolder createHeaderHolder(View view);
    protected abstract RecyclerView.ViewHolder createItemHolder(View view);

    protected abstract void processHeaderHolder(RecyclerView.ViewHolder holder);
    protected abstract void processItemHolder(RecyclerView.ViewHolder holder, T t, int position);

    public BaseRecyclerAdapter(Context context) {
        this.context = context;

        list = new LinkedList<>();
    }

    public void setListener(RecyclerViewAdapterListener listener){
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(getHeaderLayout(), parent, false);
            return createHeaderHolder(view);
        }

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((v.getTag() == null) && !(v.getTag() instanceof RecyclerView.ViewHolder)) return;

                    RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();

                    int position = getHeaderLayout() == 0 ? holder.getAdapterPosition() : holder.getAdapterPosition() - 1;
                    if (position < 0 && position > list.size()) return;
                    if (listener != null){
                        listener.onClicked(list.get(position), position);
                    }
                }
            });

            return createItemHolder(view);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int aposition) {
        int position = holder.getAdapterPosition();

        if (position == 0 && getHeaderLayout() != 0){
            processHeaderHolder(holder);
        } else {
            holder.itemView.setTag(holder);
            processItemHolder(holder, list.get(itemPosition(position)), itemPosition(position));
            if (itemPosition(position) + 1 >= list.size()) {
                if (listener != null) listener.onLastItem(list.size());
            }
        }
    }

    private int itemPosition(int position) {
        return (getHeaderLayout() == 0) ? position : position - 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 && getHeaderLayout() != 0) ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return (getHeaderLayout() == 0) ? list.size() : list.size() + 1;
    }

    public void add(T data) {
        list.add(data);
    }

    public void add(List<T> datas) {
        list.addAll(datas);
    }

    public void clear() {
        list.clear();
    }
}
