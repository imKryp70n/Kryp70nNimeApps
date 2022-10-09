package com.kryp70nnime.apps.models.AnimeEpisodeModels;

import com.google.gson.annotations.SerializedName;

public class SourcesItem{

	@SerializedName("isM3U8")
	private boolean isM3U8;

	@SerializedName("url")
	private String url;

	public boolean isIsM3U8(){
		return isM3U8;
	}

	public String getUrl(){
		return url;
	}
}