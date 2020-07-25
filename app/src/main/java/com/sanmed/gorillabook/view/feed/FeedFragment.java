package com.sanmed.gorillabook.view.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanmed.gorillabook.R;
import com.sanmed.gorillabook.databinding.FragmentFeedBinding;
import com.sanmed.gorillabook.model.database.FeedDataBase;
import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;
import com.sanmed.gorillabook.model.database.FeedDataBaseHelper;
import com.sanmed.gorillabook.view.common.FeedAdapter;
import com.sanmed.gorillabook.view.common.FeedUI;
import com.sanmed.gorillabook.view.common.FeedUIDiff;
import com.sanmed.gorillabook.viewmodel.feed.FeedViewModel;
import com.sanmed.gorillabook.viewmodel.feed.FeedViewModelFactory;

import java.util.List;

public class FeedFragment extends Fragment {

    private FeedViewModel mViewModel;
    private FeedAdapter feedAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FeedDataBaseDAO dataBaseDAO = FeedDataBaseHelper.getInstance(requireActivity().getApplication()).getFeedDataBaseDAO();
        FeedViewModelFactory feedViewModelFactory = new FeedViewModelFactory(requireActivity().getApplication(),dataBaseDAO);
        mViewModel = new ViewModelProvider(this,feedViewModelFactory).get(FeedViewModel.class);
        FragmentFeedBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed, container, false);
        binding.setViewModel(mViewModel);
        feedAdapter = new FeedAdapter(new FeedUIDiff());
        initSubscribers();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.init();

    }

    private void initSubscribers() {
        mViewModel.getFeeds().observe(getViewLifecycleOwner(),this::onFeedsChanged);
    }

    private void onFeedsChanged(List<FeedUI> feedUIS) {
        feedAdapter.submitList(feedUIS);
    }

}