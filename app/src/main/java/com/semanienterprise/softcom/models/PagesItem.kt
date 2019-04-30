package com.semanienterprise.softcom.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PagesItem : Serializable {

    @SerializedName("label")
    var label: String? = null
        set(label) {
            field = this.label
        }

    @SerializedName("sections")
    var sections: List<SectionsItem>? = null
        set(sections) {
            field = this.sections
        }

    override fun toString(): String {
        return "PagesItem{" +
                "label = '" + this.label + '\''.toString() +
                ",sections = '" + this.sections + '\''.toString() +
                "}"
    }
}