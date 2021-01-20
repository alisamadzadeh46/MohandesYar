package com.example.mohandesipezeshki.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBinding {
    @BindingAdapter("image")
    @JvmStatic
    fun Getimage(view:ImageView,url:String){
        Glide.with(view).load(url).into(view)
    }
}