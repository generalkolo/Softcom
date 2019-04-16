package com.semanienterprise.softcom.models;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.semanienterprise.softcom.ui.util.GUISpinner;
import com.semanienterprise.softcom.ui.util.GUITextInputEditText;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ElementsItem implements Serializable {

    @SerializedName("unique_id")
    private String uniqueId;

    @SerializedName("rules")
    private List<RulesItem> rules;

    @SerializedName("label")
    private String label;

    @SerializedName("file")
    private String file;

    @SerializedName("type")
    private String type;

    @SerializedName("isMandatory")
    private boolean isMandatory;

    Object obj;

    public Object getData()
    {
        if (type.equals("text") || type.equals("formattednumeric") || type.equals("datetime"))
        {
            if (obj != null) {
                GUITextInputEditText b = (GUITextInputEditText) obj;
                return b.getValue();
            }
        }
        if (type.equals("yesno")) {
            if (obj != null) {
                GUISpinner po = (GUISpinner) obj;
                return po.getValue();
            }
        }
        return null;
    }

    public String getFormattedResult()
    {
        return String.valueOf(this.getData());
    }

    @Override
    public String toString() {
        return
                "ElementsItem{" +
                        "unique_id = '" + uniqueId + '\'' +
                        ",rules = '" + rules + '\'' +
                        ",label = '" + label + '\'' +
                        ",type = '" + type + '\'' +
                        ",isMandatory = '" + isMandatory + '\'' +
                        "}";
    }
}