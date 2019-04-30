package com.semanienterprise.softcom.ui.display

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.semanienterprise.softcom.R
import com.semanienterprise.softcom.SpinnerInterface
import com.semanienterprise.softcom.models.FormData
import com.semanienterprise.softcom.ui.url.MainActivity
import com.semanienterprise.softcom.ui.util.GUIImageView
import com.semanienterprise.softcom.ui.util.GUISpinner
import com.semanienterprise.softcom.ui.util.GUITextInputEditText
import java.util.*

class DisplayPages : AppCompatActivity(), SpinnerInterface {

    private lateinit var formData: FormData
    private val dependencies = HashMap<String?, String?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSentData()
    }

    private fun getSentData() {
        val retrievedIntent = intent.extras

        retrievedIntent?.let {
            formData = retrievedIntent.getSerializable(MainActivity.DATA_KEY) as FormData

            val scrollView = ScrollView(this)
            val linearLayout = LinearLayout(this)
            scrollView.addView(linearLayout)
            scrollView.setPadding(30, 10, 30, 10)
            linearLayout.orientation = LinearLayout.VERTICAL

            val formName = TextView(this)
            formName.text = formData.name
            formName.textSize = 19f
            formName.gravity = Gravity.CENTER or Gravity.BOTTOM
            linearLayout.addView(formName)

            //looping through all the pages
            for (pagesItem in formData.pages!!) {
                val pageLabel = TextView(this)
                pageLabel.text = pagesItem.label
                pageLabel.textSize = 17f
                pageLabel.gravity = Gravity.START
                linearLayout.addView(pageLabel)

                //get the sections of each pages and loop through them
                val sections = pagesItem.sections

                for (sectionsItem in sections!!) {
                    val sectionLabel = TextView(this)
                    sectionLabel.text = sectionsItem.label
                    sectionLabel.textSize = 15f
                    linearLayout.addView(sectionLabel)

                    //get the list of elements and loop through
                    val elements = sectionsItem.elements

                    for (elementsItem in elements!!) {
                        if (elementsItem.type == "embeddedphoto") {
                            val guiImageView = GUIImageView(this, elementsItem.label, elementsItem.file)
                            guiImageView.gravity = Gravity.CENTER_HORIZONTAL
                            elementsItem.obj = guiImageView
                            linearLayout.addView(elementsItem.obj as View)
                        }
                        if (elementsItem.type == "text" || elementsItem.type == "datetime") {
                            elementsItem.obj = GUITextInputEditText(this, if (elementsItem.isMandatory) elementsItem.label + "*" else elementsItem.label)
                            //check if the unique id of the element exist in the dependency array list and set visibility to gone
                            if (dependencies.containsValue(elementsItem.uniqueId)) {

                            }
                            linearLayout.addView(elementsItem.obj as View)
                        }
                        if (elementsItem.type == "formattednumeric") {
                            val numericEditBox = GUITextInputEditText(this, if (elementsItem.isMandatory) elementsItem.label + "*" else "" + elementsItem.label)
                            numericEditBox.makeNumeric()
                            elementsItem.obj = numericEditBox

                            //check if the unique id of the element exist in the dependency array list and set visibility to gone
                            if (dependencies.containsValue(elementsItem.uniqueId)) {
                                numericEditBox.visibility = View.GONE
                            }

                            linearLayout.addView(elementsItem.obj as View)
                        }
                        if (elementsItem.type == "yesno") {
                            if (elementsItem.rules != null) {
                                val rulesItems = elementsItem.rules
                                for (ruleItem in rulesItems!!) {
                                    dependencies[elementsItem.uniqueId] = ruleItem.targets?.get(0)
                                }
                            }
                            elementsItem.obj = GUISpinner(this, elementsItem.label + if (elementsItem.isMandatory) "*" else "", "Yes|No")
                            linearLayout.addView(elementsItem.obj as View)
                        }
                    }
                }
            }
            //create a button and add to the scroll view
            val btn = Button(this)
            btn.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            btn.gravity = Gravity.CENTER_HORIZONTAL
            btn.text = getString(R.string.text_submit)
            linearLayout.addView(btn)

            btn.setOnClickListener {
                //get all the elements and check their entries based on the rules indicated
                for (pagesItem in formData.pages!!) {
                    val sections = pagesItem.sections
                    for (sectionsItem in sections!!) {
                        val elements = sectionsItem.elements
                        for (elementsItem in elements!!) {
                            if (elementsItem.isMandatory && (elementsItem.data.toString().isEmpty() || elementsItem.data == null)) {
                                if (elementsItem.obj is GUITextInputEditText) (elementsItem.obj as GUITextInputEditText).textInputLayout.error = "This field must be filled"
                            } else Toast.makeText(this@DisplayPages, "Form Correctly Filled", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            setContentView(scrollView)
        }
    }

    override fun spinnerOptionClicked(Value: String) {
        Log.d("OKay", Value)
    }
}
