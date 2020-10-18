package com.luas.app.base

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleOrGone")
fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("tintColor")
fun ImageView.tintColor(color: Int?) {
    setColorFilter(color ?: Color.BLACK)
}
