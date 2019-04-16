package com.semanienterprise.softcom.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormData implements Serializable {
	@SerializedName("pages")
	private List<PagesItem> pages;

    @SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

    @Override
 	public String toString(){
		return
			"FormData{" +
			"pages = '" + pages + '\'' +
			",name = '" + name + '\'' +
			",id = '" + id + '\'' +
			"}";
		}
}