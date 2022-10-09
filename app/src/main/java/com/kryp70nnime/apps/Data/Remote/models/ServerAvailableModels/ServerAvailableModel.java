package com.kryp70nnime.apps.Data.Remote.models.ServerAvailableModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ServerAvailableModel{

	@SerializedName("ServerAvailableModel")
	private List<ServerAvailableModelItem> serverAvailableModel;

	public List<ServerAvailableModelItem> getServerAvailableModel(){
		return serverAvailableModel;
	}
}