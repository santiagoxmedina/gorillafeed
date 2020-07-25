package com.sanmed.gorillabook.view.common;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanmed.gorillabook.databinding.ViewItemFeedBinding;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    private ViewItemFeedBinding viewItemFeedBinding;
    public FeedViewHolder(@NonNull ViewItemFeedBinding binding) {
        super(binding.getRoot());
        viewItemFeedBinding = binding;
    }

    public void onBin(FeedUI feedUI){
        viewItemFeedBinding.setFeed(feedUI);
        viewItemFeedBinding.executePendingBindings();
    }
}
