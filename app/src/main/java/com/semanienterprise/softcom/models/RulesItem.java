package com.semanienterprise.softcom.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RulesItem implements Serializable {

	@SerializedName("otherwise")
	private String otherwise;

	@SerializedName("condition")
	private String condition;

	@SerializedName("action")
	private String action;

	@SerializedName("value")
	private String value;

	@SerializedName("targets")
	private List<String> targets;

    @Override
 	public String toString(){
		return 
			"RulesItem{" + 
			"otherwise = '" + otherwise + '\'' + 
			",condition = '" + condition + '\'' + 
			",action = '" + action + '\'' + 
			",value = '" + value + '\'' + 
			",targets = '" + targets + '\'' + 
			"}";
		}
}