package com.kryp70nnime.apps.models.ServerAvailableModels;

import com.google.gson.annotations.SerializedName;

public class ServerAvailableModelItem{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private String url;

	public String getName(){
		return name;
	}

	public String getUrl(){
		return url;
	}
}