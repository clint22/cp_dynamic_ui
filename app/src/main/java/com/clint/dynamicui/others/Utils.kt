package com.clint.dynamicui.others

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

object Utils {

    fun createImageView(
        context: Context,
        marginSize: Int,
        gravity: Int,
        url: String?,
        width: Int,
        height: Int
    ): ImageView {
        val imgLogo = ImageView(context)
        val layoutParams = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = gravity
        layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize)
        imgLogo.layoutParams = layoutParams
        imgLogo.loadImage(
            context = context,
            url = url,
            width = width,
            height = height
        )
        return imgLogo
    }

    fun createTextView(
        context: Context,
        marginSize: Int,
        gravity: Int,
        text: String,
        textSize: Float = 18F,
        textColor: Int? = null,
        width: Int,
        height: Int
    ): TextView {
        val textLabel = TextView(context)
        val layoutParams = ActionBar.LayoutParams(
            width,
            height
        )
        layoutParams.gravity = gravity
        layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize)
        textLabel.layoutParams = layoutParams
        textLabel.text = text
        textLabel.textSize = textSize
        textColor?.let {
            textLabel.setTextColor(it)
        }
        return textLabel
    }

    fun createEditText(
        context: Context,
        marginSize: Int,
        gravity: Int,
        key: String?,
        backgroundResource: Int?
    ): EditText {
        val editText = EditText(context)
        val layoutParams = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        editText.layoutParams = layoutParams
        editText.inputType =
            if (key?.contains("phone") == true) InputType.TYPE_CLASS_NUMBER else InputType.TYPE_TEXT_FLAG_CAP_WORDS
        if (backgroundResource != null) {
            editText.setBackgroundResource(backgroundResource)
        }
        layoutParams.gravity = gravity
        layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize)
        return editText
    }

    fun createButton(context: Context, marginSize: Int, gravity: Int, text: String): Button {
        val button = Button(context)
        val layoutParams = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        button.layoutParams = layoutParams
        button.text = text
        button.setBackgroundColor(Color.BLACK)
        button.setTextColor(Color.WHITE)
        layoutParams.gravity = gravity
        layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize)
        return button
    }
}