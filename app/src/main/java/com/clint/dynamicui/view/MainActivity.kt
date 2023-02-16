package com.clint.dynamicui.view

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.clint.dynamicui.R
import com.clint.dynamicui.databinding.ActivityMainBinding
import com.clint.dynamicui.others.Constants.CUSTOM_URL
import com.clint.dynamicui.others.Constants.IMAGE_HEIGHT
import com.clint.dynamicui.others.Constants.IMAGE_WIDTH
import com.clint.dynamicui.others.Constants.INTENT_HASH_KEY
import com.clint.dynamicui.others.Constants.MARGIN_SIZE_10
import com.clint.dynamicui.others.Constants.MARGIN_SIZE_20
import com.clint.dynamicui.others.Constants.MARGIN_SIZE_30
import com.clint.dynamicui.others.Constants.UI_TYPE_BUTTON
import com.clint.dynamicui.others.Constants.UI_TYPE_EDIT_TEXT
import com.clint.dynamicui.others.Constants.UI_TYPE_LABEL
import com.clint.dynamicui.others.UiManager
import com.clint.dynamicui.others.Utils
import com.clint.networkmanager.data.CustomUI
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayout: LinearLayout
    private var submitButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        fetchCustomUiDetails()
        setObservers()
    }

    private fun setClickListeners() {
        binding.mainProgress.visibility = View.GONE
        submitButton?.setOnClickListener {
            val uiMap = UiManager.verifyLabelsAndSubmit(context = this)
            if (uiMap?.isNotEmpty() == true) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(INTENT_HASH_KEY, uiMap)
                startActivity(intent)
            }
        }
    }

    private fun initView() {
        linearLayout = findViewById(R.id.parent_layout)
        linearLayout.gravity = Gravity.CENTER
    }

    private fun fetchCustomUiDetails() {
        mainViewModel.fetchCustomUi(url = CUSTOM_URL)
        mainViewModel.fetchCustomLogo(url = CUSTOM_URL)
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.customUi.collect {
                        it?.let {
                            setupUi(it)
                        }
                    }
                }
                launch {
                    mainViewModel.customLogo.collect {
                        it?.let {
                            setupLogo(it)
                        }
                    }
                }
                launch {
                    mainViewModel.exception.collect {
                        it?.let {
                            setupLottie()
                        }
                    }
                }
            }
        }
    }

    private fun setupLottie() {
        binding.mainProgress.visibility = View.GONE
        binding.lottieError.visibility = View.VISIBLE
        binding.lottieError.playAnimation()
    }

    private fun setupUi(it: CustomUI) {
        it.ui_data?.forEach { item ->
            when (item.ui_type) {
                UI_TYPE_LABEL -> {
                    item.value?.let { value ->
                        val label = Utils.createTextView(
                            context = this,
                            marginSize = MARGIN_SIZE_20,
                            gravity = Gravity.START,
                            text = value,
                            textColor = Color.BLACK,
                            width = ActionBar.LayoutParams.MATCH_PARENT,
                            height = ActionBar.LayoutParams.WRAP_CONTENT
                        )
                        UiManager.updateUiMap(value)
                        UiManager.updateEdsLabel(value)
                        linearLayout.addView(label)
                    }
                }
                UI_TYPE_EDIT_TEXT -> {
                    val edittext =
                        Utils.createEditText(
                            context = this,
                            marginSize = MARGIN_SIZE_10,
                            gravity = Gravity.CENTER,
                            key = item.key,
                            backgroundResource = R.drawable.rounded_edit_text
                        )
                    UiManager.updateAllEds(edittext)
                    linearLayout.addView(edittext)
                }
                UI_TYPE_BUTTON -> {
                    submitButton =
                        item.value?.let { value ->
                            Utils.createButton(
                                context = this,
                                marginSize = MARGIN_SIZE_20,
                                gravity = Gravity.CENTER,
                                text = value
                            )
                        }
                    linearLayout.addView(submitButton)
                    setClickListeners()
                }
            }
        }
    }

    private fun setupLogo(logo: String?) {
        val imgLogo =
            Utils.createImageView(
                context = this,
                marginSize = MARGIN_SIZE_30,
                gravity = Gravity.CENTER,
                url = logo,
                width = IMAGE_WIDTH,
                height = IMAGE_HEIGHT
            )
        linearLayout.addView(imgLogo)
    }

    override fun onStop() {
        super.onStop()
        linearLayout.removeAllViews()
    }
}