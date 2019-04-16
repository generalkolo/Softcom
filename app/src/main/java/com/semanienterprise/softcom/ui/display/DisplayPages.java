package com.semanienterprise.softcom.ui.display;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.semanienterprise.softcom.R;
import com.semanienterprise.softcom.SpinnerInterface;
import com.semanienterprise.softcom.models.ElementsItem;
import com.semanienterprise.softcom.models.FormData;
import com.semanienterprise.softcom.models.PagesItem;
import com.semanienterprise.softcom.models.RulesItem;
import com.semanienterprise.softcom.models.SectionsItem;
import com.semanienterprise.softcom.ui.util.GUIImageView;
import com.semanienterprise.softcom.ui.util.GUISpinner;
import com.semanienterprise.softcom.ui.util.GUITextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayPages extends AppCompatActivity implements SpinnerInterface {

    private FormData formData;
    private HashMap<String, String> dependencies = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSentData();
    }

    private void getSentData() {
        if (getIntent() != null) {
            formData = (FormData) getIntent().getSerializableExtra("formData");

            final FormData formData = (FormData) getIntent().getSerializableExtra("formData");

            ScrollView scrollView = new ScrollView(this);
            final LinearLayout linearLayout = new LinearLayout(this);
            scrollView.addView(linearLayout);
            scrollView.setPadding(30, 10, 30, 10);
            linearLayout.setOrientation(android.widget.LinearLayout.VERTICAL);

            TextView formName = new TextView(this);
            formName.setText(formData.getName());
            formName.setTextSize(19);
            formName.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            formName.setTextColor(getResources().getColor(R.color.primaryTextColor));
            linearLayout.addView(formName);

            //looping through all the pages
            for (PagesItem pagesItem : formData.getPages()) {
                TextView pageLabel = new TextView(this);
                pageLabel.setText(pagesItem.getLabel());
                pageLabel.setTextSize(17);
                pageLabel.setGravity(Gravity.START);
                pageLabel.setTextColor(getResources().getColor(R.color.primaryTextColor));
                linearLayout.addView(pageLabel);

                //get the sections of each pages and loop through them
                List<SectionsItem> sections = pagesItem.getSections();

                for (SectionsItem sectionsItem : sections) {
                    TextView sectionLabel = new TextView(this);
                    sectionLabel.setText(sectionsItem.getLabel());
                    sectionLabel.setTextSize(15);
                    sectionLabel.setTextColor(getResources().getColor(R.color.primaryTextColor));
                    linearLayout.addView(sectionLabel);

                    //get the list of elements and loop through
                    List<ElementsItem> elements = sectionsItem.getElements();

                    for (ElementsItem elementsItem : elements) {
                        if (elementsItem.getType().equals("embeddedphoto")) {
                            GUIImageView guiImageView = new GUIImageView(this, elementsItem.getLabel(), elementsItem.getFile());
                            guiImageView.setGravity(Gravity.CENTER_HORIZONTAL);
                            elementsItem.setObj(guiImageView);
                            linearLayout.addView((View) elementsItem.getObj());
                        }
                        if (elementsItem.getType().equals("text") || elementsItem.getType().equals("datetime")) {
                            elementsItem.setObj(new GUITextInputEditText(this, elementsItem.isMandatory() ? elementsItem.getLabel() + "*" : elementsItem.getLabel()));
                            //check if the unique id of the element exist in the dependency array list and set visibility to gone
                            if (dependencies.containsValue(elementsItem.getUniqueId())) {

                            }
                            linearLayout.addView((View) elementsItem.getObj());
                        }
                        if (elementsItem.getType().equals("formattednumeric")) {
                            GUITextInputEditText numericEditBox = new GUITextInputEditText(this, elementsItem.isMandatory() ? elementsItem.getLabel() + "*" : "" + elementsItem.getLabel());
                            numericEditBox.makeNumeric();
                            elementsItem.setObj(numericEditBox);

                            //check if the unique id of the element exist in the dependency array list and set visibility to gone
                            if (dependencies.containsValue(elementsItem.getUniqueId())) {
                                numericEditBox.setVisibility(View.GONE);
                            }

                            linearLayout.addView((View) elementsItem.getObj());
                        }
                        if (elementsItem.getType().equals("yesno")) {
                            if (elementsItem.getRules() != null) {
                                List<RulesItem> rulesItems = elementsItem.getRules();
                                for (RulesItem ruleItem : rulesItems) {
                                    dependencies.put(elementsItem.getUniqueId(), ruleItem.getTargets().get(0));
                                }
                            }
                            elementsItem.setObj(new GUISpinner(this, elementsItem.getLabel() + (elementsItem.isMandatory() ? "*" : ""), "Yes|No"));
                            linearLayout.addView((View) elementsItem.getObj());
                        }
                    }
                }
            }
            //create a button and add to the scroll view
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setGravity(Gravity.CENTER_HORIZONTAL);
            btn.setText("Submit");
            linearLayout.addView(btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get all the elements and check their entries based on the rules indicated
                    for (PagesItem pagesItem : formData.getPages()) {
                        List<SectionsItem> sections = pagesItem.getSections();
                        for (SectionsItem sectionsItem : sections) {
                            List<ElementsItem> elements = sectionsItem.getElements();
                            for (ElementsItem elementsItem : elements) {
                                if (elementsItem.isMandatory() && (String.valueOf(elementsItem.getData()).isEmpty() || elementsItem.getData() == null))
                                    Toast.makeText(DisplayPages.this, "Form Incorrectly Filled", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(DisplayPages.this, "Form Correctly Filled", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
            setContentView(scrollView);
        }
    }

    @Override
    public void spinnerOptionClicked(String Value) {
        Log.d("OKay", Value);
    }
}
