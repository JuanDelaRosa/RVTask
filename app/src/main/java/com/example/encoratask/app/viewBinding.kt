package com.example.encoratask.app
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
@BindingAdapter("paletteImage")
fun bindLoadImage(view: AppCompatImageView, url: String?) {
    if (!url.isNullOrEmpty())
        Picasso.get().load(url).into(view)
}