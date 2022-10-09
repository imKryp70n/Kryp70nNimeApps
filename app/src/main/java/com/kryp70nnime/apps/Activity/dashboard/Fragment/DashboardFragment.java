package com.kryp70nnime.apps.Activity.dashboard.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.adapters.AnimeListAdapter;

import java.util.Objects;


public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    AnimeListAdapter animeListAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.animeListRecycler);

        animeListAdapter = new AnimeListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(animeListAdapter);
        recyclerView.setHorizontalScrollBarEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}