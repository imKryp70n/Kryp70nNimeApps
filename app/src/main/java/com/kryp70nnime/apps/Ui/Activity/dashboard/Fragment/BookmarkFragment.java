package com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Ui.adapters.anime.AnimeBookmarkAdapter;

public class BookmarkFragment extends Fragment {


    AnimeBookmarkAdapter animeBookmarkAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animeBookmarkAdapter = new AnimeBookmarkAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.bookmarkRecycle);

        recyclerView.setAdapter(animeBookmarkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }
}