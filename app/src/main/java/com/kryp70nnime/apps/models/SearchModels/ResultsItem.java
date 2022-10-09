package com.kryp70nnime.apps.models.SearchModels;

import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("releaseDate")
	private String releaseDate;

	@SerializedName("subOrDub")
	private String subOrDub;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	public String getImage(){
		return image;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getSubOrDub(){
		return subOrDub;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}
}