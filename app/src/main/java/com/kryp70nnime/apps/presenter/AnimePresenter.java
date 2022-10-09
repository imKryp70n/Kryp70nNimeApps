package com.kryp70nnime.apps.presenter;

import com.kryp70nnime.apps.PreferencesHelper.PreferencesHelper;
import com.kryp70nnime.apps.api.ApiService;
import com.kryp70nnime.apps.api.Constant;
import com.kryp70nnime.apps.models.TopAiringModels.TopAiringModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimePresenter {

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    public AnimePresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getTopAiring() {
        apiService.getTopAiring().enqueue(new Callback<TopAiringModel>() {
            @Override
            public void onResponse(Call<TopAiringModel> call, Response<TopAiringModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            if (response.body().getResults().size() > 0) {
                            }
                        }
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<TopAiringModel> call, Throwable t) {

            }
        });
    }

}

