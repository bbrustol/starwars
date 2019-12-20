package com.bbrustol.maytheforcebewithbruno.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("android:src")
fun setSrcVector(view: ImageView, @DrawableRes drawable: Int) {
    view.setImageResource(drawable)
}

fun RecyclerView.loadMore(onLoadMore: () -> Unit){
    this.addOnScrollListener(EndlessRecyclerOnScrollListener(onLoadMore))
}