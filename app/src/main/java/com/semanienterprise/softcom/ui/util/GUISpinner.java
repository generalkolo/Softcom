package com.semanienterprise.softcom.ui.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.semanienterprise.softcom.SpinnerInterface;

public class GUISpinner extends LinearLayout {
    TextView label;
    ArrayAdapter<String> aa;
    Spinner spinner;
    SpinnerInterface spinnerInterface;

    public GUISpinner(Context context, String labelText, String options) {
        super(context);
        spinnerInterface = (SpinnerInterface)context;
        label = new TextView(context);
        label.setText(labelText);
        spinner = new Spinner(context);
        String[] opts = options.split("\\|");
        aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opts);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerInterface.spinnerOptionClicked(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        this.addView(label);
        this.addView(spinner);
    }

    public GUISpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public String getValue() {
        return spinner.getSelectedItem().toString();
    }
}
