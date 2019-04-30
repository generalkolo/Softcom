package com.semanienterprise.softcom.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RulesItem : Serializable {

    @SerializedName("otherwise")
    var otherwise: String? = null
        set(otherwise) {
            field = this.otherwise
        }

    @SerializedName("condition")
    var condition: String? = null
        set(condition) {
            field = this.condition
        }

    @SerializedName("action")
    var action: String? = null
        set(action) {
            field = this.action
        }

    @SerializedName("value")
    var value: String? = null
        set(value) {
            field = this.value
        }

    @SerializedName("targets")
    var targets: List<String>? = null
        set(targets) {
            field = this.targets
        }

    override fun toString(): String {
        return "RulesItem{" +
                "otherwise = '" + this.otherwise + '\''.toString() +
                ",condition = '" + this.condition + '\''.toString() +
                ",action = '" + this.action + '\''.toString() +
                ",value = '" + this.value + '\''.toString() +
                ",targets = '" + this.targets + '\''.toString() +
                "}"
    }
}