package com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Ui.adapters.anime.AnimeTrendingAdapter;
import com.kryp70nnime.apps.Data.Remote.api.Constant;


public class TrendingFragment extends Fragment {
    private RecyclerView recyclerView;
    AnimeTrendingAdapter animeTrendingAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final FragmentManager fragmentManager = getFragmentManager();

        recyclerView = view.findViewById(R.id.animeTrendingRecycler);
        animeTrendingAdapter = new AnimeTrendingAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(animeTrendingAdapter);

        SearchView searchView = view.findViewById(R.id.searchViewAnime);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)   {
                // intent add extra to search anime fragment
                SearchAnimeFragment searchAnimeFragment = new SearchAnimeFragment();
                Constant.QuerySearch = query;

                fragmentManager.beginTransaction().replace(R.id.dashboardContainer, searchAnimeFragment).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Constant.QuerySearch = newText;
                return true;

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }
}