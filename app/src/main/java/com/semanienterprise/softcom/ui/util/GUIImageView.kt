package com.semanienterprise.softcom.ui.util

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.semanienterprise.softcom.R
import com.squareup.picasso.Picasso

class GUIImageView(receivedContext: Context, labelText: String?, url: String?) : LinearLayout(receivedContext) {
    private var label: TextView = TextView(receivedContext)
    private var imageView: ImageView

    init {
        label.text = labelText
        imageView = ImageView(receivedContext)
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).resize(550, 550).into(imageView)
        imageView.layoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this.addView(label)
        this.addView(imageView)
    }
}
