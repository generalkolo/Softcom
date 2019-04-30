package com.semanienterprise.softcom.ui.util

import android.content.Context
import android.text.Editable
import android.text.method.DigitsKeyListener
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class GUITextInputEditText(receivedContext: Context, labelText: String?) : LinearLayout(receivedContext) {
    var textInputLayout: TextInputLayout
    private var editText: TextInputEditText = TextInputEditText(context)

    val value: String
        get() = Objects.requireNonNull<Editable>(editText.text).toString()

    init {
        editText.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val editTextParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        val textInputLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        textInputLayout = TextInputLayout(context)

        textInputLayout.layoutParams = textInputLayoutParams
        textInputLayout.addView(editText, editTextParams)
        textInputLayout.hint = labelText

        this.addView(textInputLayout)
    }

    fun makeNumeric() {
        val dkl = DigitsKeyListener(true, true)
        editText.keyListener = dkl
    }
}
