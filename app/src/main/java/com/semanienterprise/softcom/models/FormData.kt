package com.semanienterprise.softcom.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FormData : Serializable {

    @SerializedName("pages")
    var pages: List<PagesItem>? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: String? = null

    override fun toString(): String {
        return "FormData{" +
                "pages = '" + this.pages + '\''.toString() +
                ",name = '" + this.name + '\''.toString() +
                ",id = '" + this.id + '\''.toString() +
                "}"
    }
}