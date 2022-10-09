package com.kryp70nnime.apps.Ui.Activity.detail.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Ui.adapters.anime.AnimeEpsAdapter;


public class EpisodesFragment extends Fragment {

    private RecyclerView epsRecycler;
    AnimeEpsAdapter animeEpsAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        epsRecycler = view.findViewById(R.id.totalEpsRV);
        epsRecycler.getItemAnimator().setChangeDuration(0);
        animeEpsAdapter = new AnimeEpsAdapter();
        epsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        epsRecycler.setAdapter(animeEpsAdapter);
        epsRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        epsRecycler.setHorizontalScrollBarEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episodes, container, false);
    }
}