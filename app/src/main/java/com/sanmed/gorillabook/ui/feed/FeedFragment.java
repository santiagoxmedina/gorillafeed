package com.sanmed.gorillabook.ui.feed;

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
import com.sanmed.gorillabook.viewmodel.feed.FeedViewModel;
import com.sanmed.gorillabook.viewmodel.feed.FeedViewModelFactory;

public class FeedFragment extends Fragment {

    private FeedViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FeedDataBaseDAO dataBaseDAO = FeedDataBase.getInstance(requireActivity().getApplication()).getFeedDataBaseDAO();
        FeedViewModelFactory feedViewModelFactory = new FeedViewModelFactory(requireActivity().getApplication(),dataBaseDAO);
        mViewModel = new ViewModelProvider(this,feedViewModelFactory).get(FeedViewModel.class);
        FragmentFeedBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed, container, false);
        binding.setViewModel(mViewModel);
        initSubscribers();
        return binding.getRoot();
    }

    private void initSubscribers() {

    }


}