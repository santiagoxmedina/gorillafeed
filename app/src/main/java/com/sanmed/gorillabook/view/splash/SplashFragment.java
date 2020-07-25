package com.sanmed.gorillabook.view.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanmed.gorillabook.R;
import com.sanmed.gorillabook.model.database.FeedDataBase;
import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;
import com.sanmed.gorillabook.viewmodel.splash.SplashViewModel;
import com.sanmed.gorillabook.viewmodel.splash.SplashViewModelFactory;


public class SplashFragment extends Fragment {

    private SplashViewModel mViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FeedDataBaseDAO  dataBaseDAO = FeedDataBase.getInstance(requireActivity().getApplication()).getFeedDataBaseDAO();
        SplashViewModelFactory viewModelFactory = new SplashViewModelFactory(requireActivity().getApplication(),dataBaseDAO);
        mViewModel = new ViewModelProvider(this,viewModelFactory).get(SplashViewModel.class);
        initSubscribers();
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.init();
    }

    private void initSubscribers() {
        mViewModel.getDataLoadedSuccess().observe(getViewLifecycleOwner(),this::onDataLoadedSuccess);
    }

    private void onDataLoadedSuccess(Boolean onDataLoaded) {
        if(onDataLoaded){
            gotToFeedScreen();
        }
    }

    private void gotToFeedScreen() {
        Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_feedFragment);

    }
}