package com.kryp70nnime.apps.Data.Remote.models.InfoModels;

import com.google.gson.annotations.SerializedName;

public class EpisodesItem{

	@SerializedName("number")
	private int number;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	public int getNumber(){
		return number;
	}

	public String getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}
}