package com.sanmed.gorillabook.view.common;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class FeedUIDiff extends DiffUtil.ItemCallback<FeedUI> {
    @Override
    public boolean areItemsTheSame(@NonNull FeedUI oldItem, @NonNull FeedUI newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull FeedUI oldItem, @NonNull FeedUI newItem) {
        return oldItem.date.equals(newItem.date) && oldItem.name.equals(newItem.name) && oldItem.post.equals(newItem.post) && oldItem.image == newItem.image;
    }
}
