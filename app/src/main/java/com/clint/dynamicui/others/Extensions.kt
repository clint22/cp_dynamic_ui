package com.clint.dynamicui.others

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.Serializable

fun ImageView.loadImage(context: Context, url: String?, width: Int, height: Int) {
    Glide.with(context).load(url).override(width, height).into(this)
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(
        key,
        T::class.java
    )
    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}