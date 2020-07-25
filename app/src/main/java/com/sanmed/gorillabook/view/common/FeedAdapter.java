package com.sanmed.gorillabook.view.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sanmed.gorillabook.R;
import com.sanmed.gorillabook.databinding.ViewItemFeedBinding;


public class FeedAdapter extends ListAdapter<FeedUI, RecyclerView.ViewHolder> {

    public FeedAdapter(@NonNull DiffUtil.ItemCallback<FeedUI> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewItemFeedBinding viewItemFeedBinding = DataBindingUtil.inflate(inflater,R.layout.view_item_feed,parent,false);
        FeedViewHolder feedViewHolder = new FeedViewHolder(viewItemFeedBinding);
        return feedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FeedViewHolder){
            ((FeedViewHolder)holder).onBin(getItem(position));
        }
    }
}
