package com.android.exchange.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("image")
fun setImage(view: ImageView, image: String) {
    Glide.with(view.context)
        .load(image)
        .into(view)
}


@BindingAdapter("coinValue")
fun setCoinValue(view: TextView, coinValue: Double) {
    view.text = "$$coinValue"
}


@BindingAdapter("subCoinValue")
fun setSubCoinValue(view: TextView, subCoinValue: Double) {
    view.text = "$subCoinValue BTC"
}