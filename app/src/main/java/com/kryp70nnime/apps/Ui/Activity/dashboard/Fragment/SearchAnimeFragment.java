package com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Ui.adapters.anime.AnimeRecentEpsAdapter;
import com.kryp70nnime.apps.Ui.adapters.anime.AnimeSearchResultAdapter;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.SearchModels.ResultsItem;
import com.kryp70nnime.apps.Data.Remote.models.SearchModels.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchAnimeFragment extends Fragment {


    ApiService apiService;
    AnimeSearchResultAdapter animeSearchResultAdapter;
    AnimeRecentEpsAdapter animeRecentEpsAdapter;
    SearchResponse searchResponse;
    List<ResultsItem> resultsItem;

    String Query;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.animeSearchRecycler);
        LinearLayout layoutNotFound = view.findViewById(R.id.emptySearch);
        TextView txtRecent = view.findViewById(R.id.txtRecent);
        animeSearchResultAdapter = new AnimeSearchResultAdapter();
        animeRecentEpsAdapter = new AnimeRecentEpsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(animeRecentEpsAdapter);
        // ---- SEARCH SOME ANIME ----


        SearchView searchView = view.findViewById(R.id.searchViewAnime);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                sweetAlertDialog.setTitleText("Loading");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiService = retrofit.create(ApiService.class);
                txtRecent.setText("Search Result");
                apiService.getSearchAnime(query).enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getResults().size() > 0) {
                                sweetAlertDialog.dismiss();
                                searchResponse = response.body();
                                resultsItem = searchResponse.getResults();
                                for (int i = 0; i < resultsItem.size(); i++) {
                                    Log.d("Title", resultsItem.get(i).getTitle());
                                    animeSearchResultAdapter.setAnimeResult(resultsItem);
                                }
                                recyclerView.setAdapter(animeSearchResultAdapter);
                            } else {
                                sweetAlertDialog.dismiss();
                                Dialog dialogNotFound = new Dialog(getContext());
                                dialogNotFound.setContentView(R.layout.not_found);
                                dialogNotFound.getContext();
                                dialogNotFound.setCancelable(false);
                                Button btn_ok = dialogNotFound.findViewById(R.id.btn_ok);
                                btn_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialogNotFound.dismiss();
                                    }
                                });
                                dialogNotFound.show();
                            }
                        } else {
                            Log.d("ERROR CODE : ", String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_anime, container, false);
    }
}