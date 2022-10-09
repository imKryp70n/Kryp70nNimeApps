package com.kryp70nnime.apps.models.InfoModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InfoModel{

	@SerializedName("image")
	private String image;

	@SerializedName("totalEpisodes")
	private int totalEpisodes;

	@SerializedName("releaseDate")
	private String releaseDate;

	@SerializedName("subOrDub")
	private String subOrDub;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private String type;

	@SerializedName("url")
	private String url;

	@SerializedName("genres")
	private List<String> genres;

	@SerializedName("otherName")
	private String otherName;

	@SerializedName("id")
	private String id;

	@SerializedName("episodes")
	private List<EpisodesItem> episodes;

	@SerializedName("status")
	private String status;

	public String getImage(){
		return image;
	}

	public int getTotalEpisodes(){
		return totalEpisodes;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getSubOrDub(){
		return subOrDub;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}

	public String getType(){
		return type;
	}

	public String getUrl(){
		return url;
	}

	public List<String> getGenres(){
		return genres;
	}

	public String getOtherName(){
		return otherName;
	}

	public String getId(){
		return id;
	}

	public List<EpisodesItem> getEpisodes(){
		return episodes;
	}

	public String getStatus(){
		return status;
	}
}