package com.kryp70nnime.apps.Data.Remote.models.TopAiringModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("genres")
	private List<String> genres;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	public String getImage(){
		return image;
	}

	public List<String> getGenres(){
		return genres;
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