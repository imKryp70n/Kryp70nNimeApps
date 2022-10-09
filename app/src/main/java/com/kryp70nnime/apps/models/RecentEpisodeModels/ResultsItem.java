package com.kryp70nnime.apps.models.RecentEpisodeModels;

import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("id")
	private String id;

	@SerializedName("episodeId")
	private String episodeId;

	@SerializedName("title")
	private String title;

	@SerializedName("episodeNumber")
	private int episodeNumber;

	@SerializedName("url")
	private String url;

	public String getImage(){
		return image;
	}

	public String getId(){
		return id;
	}

	public String getEpisodeId(){
		return episodeId;
	}

	public String getTitle(){
		return title;
	}

	public int getEpisodeNumber(){
		return episodeNumber;
	}

	public String getUrl(){
		return url;
	}
}