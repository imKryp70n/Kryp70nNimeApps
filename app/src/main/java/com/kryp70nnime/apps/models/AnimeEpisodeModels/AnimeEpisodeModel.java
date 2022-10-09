package com.kryp70nnime.apps.models.AnimeEpisodeModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AnimeEpisodeModel{

	@SerializedName("headers")
	private Headers headers;

	@SerializedName("sources")
	private List<SourcesItem> sources;

	public Headers getHeaders(){
		return headers;
	}

	public List<SourcesItem> getSources(){
		return sources;
	}
}