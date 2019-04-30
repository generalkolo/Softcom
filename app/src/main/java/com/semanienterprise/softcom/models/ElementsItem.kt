package com.semanienterprise.softcom.models


import com.google.gson.annotations.SerializedName
import com.semanienterprise.softcom.ui.util.GUISpinner
import com.semanienterprise.softcom.ui.util.GUITextInputEditText
import java.io.Serializable


class ElementsItem : Serializable {

    @SerializedName("unique_id")
    var uniqueId: String? = null
        set(uniqueId) {
            field = this.uniqueId
        }

    @SerializedName("rules")
    var rules: List<RulesItem>? = null
        set(rules) {
            field = this.rules
        }

    @SerializedName("label")
    var label: String? = null
        set(label) {
            field = this.label
        }

    @SerializedName("file")
    var file: String? = null
        set(file) {
            field = this.file
        }

    @SerializedName("type")
    var type: String? = null
        set(type) {
            field = this.type
        }

    @SerializedName("isMandatory")
    var isMandatory: Boolean = false
        set(isMandatory) {
            field = this.isMandatory
        }

    internal var obj: Any? = null

    val data: Any?
        get() {
            if (this.type == "text" || this.type == "formattednumeric" || this.type == "datetime") {
                if (obj != null) {
                    val b = obj as GUITextInputEditText?
                    return b!!.value
                }
            }
            if (this.type == "yesno") {
                if (obj != null) {
                    val po = obj as GUISpinner?
                    return po!!.value
                }
            }
            return null
        }

    val formattedResult: String
        get() = this.data.toString()

    override fun toString(): String {
        return "ElementsItem{" +
                "unique_id = '" + this.uniqueId + '\''.toString() +
                ",rules = '" + this.rules + '\''.toString() +
                ",label = '" + this.label + '\''.toString() +
                ",type = '" + this.type + '\''.toString() +
                ",isMandatory = '" + this.isMandatory + '\''.toString() +
                "}"
    }
}