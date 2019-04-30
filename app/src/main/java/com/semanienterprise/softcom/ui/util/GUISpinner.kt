package com.semanienterprise.softcom.ui.util

import android.content.Context
import android.view.View
import android.widget.*
import com.semanienterprise.softcom.SpinnerInterface

class GUISpinner(context: Context, labelText: String, options: String) : LinearLayout(context) {
    private var label: TextView = TextView(context)
    private var aa: ArrayAdapter<String>
    internal var spinner: Spinner
    internal var spinnerInterface: SpinnerInterface = context as SpinnerInterface

    init {
        label.text = labelText
        spinner = Spinner(context)
        val opts = options.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        aa = ArrayAdapter(context, android.R.layout.simple_spinner_item, opts)
        spinner.adapter = aa

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                spinnerInterface.spinnerOptionClicked(spinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        this.addView(label)
        this.addView(spinner)
    }

    val value: String
        get() = spinner.selectedItem.toString()
}
