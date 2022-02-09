package com.example.heros.adapters

import android.provider.MediaStore.Files.FileColumns.MIME_TYPE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.heros.R

//https://bumptech.github.io/glide/doc/caching.html
//https://tech.fleka.me/how-to-have-an-expired-cache-for-glide-image-library-e69d9b54ef40
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .error(R.drawable.ic_launcher_foreground)
//            .signature(DiskCacheStrategy.RESOURCE) TODO
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}