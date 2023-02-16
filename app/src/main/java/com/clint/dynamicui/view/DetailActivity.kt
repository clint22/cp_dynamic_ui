package com.clint.dynamicui.view

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.clint.dynamicui.R
import com.clint.dynamicui.databinding.ActivityDetailBinding
import com.clint.dynamicui.others.Constants.INTENT_HASH_KEY
import com.clint.dynamicui.others.Constants.MARGIN_SIZE_10
import com.clint.dynamicui.others.Constants.MARGIN_SIZE_20
import com.clint.dynamicui.others.Utils
import com.clint.dynamicui.others.serializable

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentDetails()
    }

    private fun getIntentDetails() {
        val intent = intent
        val hashMap = intent.serializable(INTENT_HASH_KEY) as HashMap<String, String>?
        hashMap?.let {
            setupView(hashMap)
        }
    }

    private fun setupView(hashMap: HashMap<String, String>) {
        linearLayout = findViewById(R.id.parent_sub_layout)
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(MARGIN_SIZE_20)
        hashMap.forEach { (key, value) ->
            val txtLabel = Utils.createTextView(
                context = this,
                marginSize = MARGIN_SIZE_10,
                gravity = Gravity.CENTER,
                text = key,
                width = ActionBar.LayoutParams.WRAP_CONTENT,
                height = ActionBar.LayoutParams.WRAP_CONTENT
            )
            linearLayout.addView(txtLabel)
            val txtValue = Utils.createTextView(
                context = this,
                marginSize = MARGIN_SIZE_10,
                gravity = Gravity.CENTER,
                text = value,
                textColor = Color.BLACK,
                textSize = 22F,
                width = ActionBar.LayoutParams.WRAP_CONTENT,
                height = ActionBar.LayoutParams.WRAP_CONTENT
            )
            linearLayout.addView(txtValue)
        }
    }
}