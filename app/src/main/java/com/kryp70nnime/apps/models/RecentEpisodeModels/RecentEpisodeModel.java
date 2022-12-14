package com.kryp70nnime.apps.models.RecentEpisodeModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RecentEpisodeModel{

	@SerializedName("hasNextPage")
	private boolean hasNextPage;

	@SerializedName("currentPage")
	private int currentPage;

	@SerializedName("results")
	private List<ResultsItem> results;

	public boolean isHasNextPage(){
		return hasNextPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}