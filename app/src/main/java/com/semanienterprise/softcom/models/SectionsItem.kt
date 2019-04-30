package com.semanienterprise.softcom.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SectionsItem : Serializable {

    @SerializedName("elements")
    var elements: List<ElementsItem>? = null
        set(elements) {
            field = this.elements
        }

    @SerializedName("label")
    var label: String? = null
        set(label) {
            field = this.label
        }

    val formattedResults: String
        get() {
            val sb = StringBuilder()
            sb.append("Results:\n")
            if (this.elements == null) return sb.toString()
            for (element in this.elements!!) {
                sb.append(element.formattedResult + "\n")
            }
            return sb.toString()
        }

    override fun toString(): String {
        return "SectionsItem{" +
                "elements = '" + this.elements + '\''.toString() +
                ",label = '" + this.label + '\''.toString() +
                "}"
    }
}