package com.example.kotlincountrieslist.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlincountrieslist.R

/*
fun String.myEctension(myParameter:String){
    println( myParameter)
}

 */

fun ImageView.DownloadFromUrl(url: String,progressDrawable: CircularProgressDrawable){

    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}
fun plaseholderProgressBar(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {

        strokeWidth = 8f
        centerRadius = 40f
        start()

    }
}