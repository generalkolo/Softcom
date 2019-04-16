package com.semanienterprise.softcom.ui.util;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Objects;

public class GUITextInputEditText extends LinearLayout {
    TextInputLayout textInputLayout;
    TextInputEditText editText;

    public GUITextInputEditText(Context context, String labelText) {
        super(context);
        editText = new TextInputEditText(context);
        editText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        textInputLayout = new TextInputLayout(context);

        textInputLayout.setLayoutParams(textInputLayoutParams);
        textInputLayout.addView(editText, editTextParams);
        textInputLayout.setHint(labelText);

        this.addView(textInputLayout);
    }

    public GUITextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public void makeNumeric() {
        DigitsKeyListener dkl = new DigitsKeyListener(true, true);
        editText.setKeyListener(dkl);
    }

    public String getValue() {
        return Objects.requireNonNull(editText.getText()).toString();
    }
}
