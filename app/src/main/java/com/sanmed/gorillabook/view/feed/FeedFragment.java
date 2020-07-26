package com.sanmed.gorillabook.view.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanmed.gorillabook.R;
import com.sanmed.gorillabook.databinding.FragmentFeedBinding;
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
    private FragmentFeedBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FeedDataBaseDAO dataBaseDAO = FeedDataBaseHelper.getInstance(requireActivity().getApplication()).getFeedDataBaseDAO();
        FeedViewModelFactory feedViewModelFactory = new FeedViewModelFactory(requireActivity().getApplication(),dataBaseDAO);
        mViewModel = new ViewModelProvider(this,feedViewModelFactory).get(FeedViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed, container, false);
        feedAdapter = new FeedAdapter(new FeedUIDiff());
        binding.feedsList.setAdapter(feedAdapter);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(mViewModel);
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
        mViewModel.getGotToCreatePost().observe(getViewLifecycleOwner(),this::onGoToCreatePost);
    }

    private void onGoToCreatePost(Boolean goToCreatePost) {
        if(goToCreatePost){
            goToCreatePost();
            mViewModel.onGotToCreatePostCompleted();
        }
    }

    private void goToCreatePost() {
        Navigation.findNavController(requireView()).navigate(R.id.action_feedFragment_to_createPostFragment);
    }

    private void onFeedsChanged(List<FeedUI> feedUIS) {
        feedAdapter.submitList(feedUIS);
    }

}