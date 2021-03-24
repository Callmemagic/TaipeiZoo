package com.joe.taipeizoo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * author: Joe Cheng
 */
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String) {

    Glide.with(imageView.context)
        .load(url)
        .error(R.mipmap.ic_launcher)
//        .apply( RequestOptions().override(400, 400))
        .centerCrop()
        .into(imageView)
}