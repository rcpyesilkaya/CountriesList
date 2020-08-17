package com.recepyesilkaya.countrieslist.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.recepyesilkaya.countrieslist.R

fun ImageView.downloadImageFromUrl(url: String?,progressDrawable: CircularProgressDrawable){

    val options=RequestOptions()
        .placeholder(getPlaceHolder(context))
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .load(url)
        .into(this)

}

fun getPlaceHolder(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth= 8f
        centerRadius=40f
        start()
    }
}

@BindingAdapter("android:downloadImgUrl")
fun downloadImgUrl(view:ImageView,url:String?) {
    view.downloadImageFromUrl(url, getPlaceHolder(view.context))
}

