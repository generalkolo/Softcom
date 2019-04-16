package com.semanienterprise.softcom.ui.util;

import android.content.Context;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.semanienterprise.softcom.R;
import com.squareup.picasso.Picasso;

public class GUIImageView extends LinearLayout {
    TextView label;
    ImageView imageView;

    public GUIImageView(Context context, String labelText, String url) {
        super(context);
        label = new TextView(context);
        label.setText(labelText);
        imageView = new ImageView(context);
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).resize(550, 550).into(imageView);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.addView(label);
        this.addView(imageView);
    }
}
