package com.joe.taipeizoo

import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import android.widget.TextView
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
        .error(R.drawable.ic_outline_no_photography_24)
//        .apply( RequestOptions().override(400, 400))
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("htmlText")
fun formatHtml(textView : TextView, url : String) {
    textView.text =
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
    {
        Html.fromHtml(url, Html.FROM_HTML_MODE_LEGACY)
    }
    else
    {
        Html.fromHtml(url);
    }
}