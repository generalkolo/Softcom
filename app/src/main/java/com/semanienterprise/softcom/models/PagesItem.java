package com.semanienterprise.softcom.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagesItem implements Serializable {

	@SerializedName("label")
	private String label;

	@SerializedName("sections")
	private List<SectionsItem> sections;

    @Override
 	public String toString(){
		return 
			"PagesItem{" + 
			"label = '" + label + '\'' + 
			",sections = '" + sections + '\'' + 
			"}";
		}
}