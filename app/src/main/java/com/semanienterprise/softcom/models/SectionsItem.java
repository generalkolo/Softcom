package com.semanienterprise.softcom.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionsItem implements Serializable {

    @SerializedName("elements")
    private List<ElementsItem> elements;

    @SerializedName("label")
    private String label;

    public String getFormattedResults() {
        StringBuilder sb = new StringBuilder();
        sb.append("Results:\n");
        if (this.elements == null) return sb.toString();
        for (ElementsItem element : this.elements) {
            sb.append(element.getFormattedResult() + "\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return
                "SectionsItem{" +
                        "elements = '" + elements + '\'' +
                        ",label = '" + label + '\'' +
                        "}";
    }
}