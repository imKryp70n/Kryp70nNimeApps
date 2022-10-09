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
import com.kryp70nnime.apps.Ui.adapters.anime.AnimeListAdapter;
import com.kryp70nnime.apps.Ui.adapters.anime.AnimeSearchResultAdapter;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.SearchModels.ResultsItem;
import com.kryp70nnime.apps.Data.Remote.models.SearchModels.SearchResponse;

import java.util.List;


public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    AnimeListAdapter animeListAdapter;
    ApiService apiService;
    List<ResultsItem> resultsItem;
    SearchResponse searchResponse;
    AnimeSearchResultAdapter animeSearchResultAdapter;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        recyclerView = view.findViewById(R.id.animeListRecycler);
        animeSearchResultAdapter = new AnimeSearchResultAdapter();
        final FragmentManager fragmentManager = getFragmentManager();
        SearchView searchView = view.findViewById(R.id.searchViewAnime);
        animeListAdapter = new AnimeListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(animeListAdapter);
        recyclerView.setHorizontalScrollBarEnabled(true);


        // ---- SEARCH SOME ANIME ----
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

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}