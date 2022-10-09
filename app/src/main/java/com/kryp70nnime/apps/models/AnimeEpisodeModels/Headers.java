package com.kryp70nnime.apps.models.AnimeEpisodeModels;

import com.google.gson.annotations.SerializedName;

public class Headers{

	@SerializedName("Referer")
	private String referer;

	public String getReferer(){
		return referer;
	}
}