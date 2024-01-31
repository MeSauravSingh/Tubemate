package com.example.tubemate.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tubemate.R

fun ImageView.setGlide(context: Context, image: String){
    Glide.with(context)
        .load(image)
        .apply(RequestOptions().placeholder(R.drawable.placeholder))
        .into(this)

}





