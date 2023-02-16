package com.clint.dynamicui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clint.dynamicui.others.Constants.COMMON_ERROR_MESSAGE
import com.clint.networkmanager.data.CustomUI
import com.clint.networkmanager.manager.CustomUiNetworkManager
import com.clint.networkmanager.manager.CustomUiNetworkManagerImpl
import com.clint.networkmanager.others.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val customUiNetworkManager: CustomUiNetworkManager by lazy {
        CustomUiNetworkManagerImpl()
    }

    private val _customUi = MutableStateFlow<CustomUI?>(null)
    val customUi: StateFlow<CustomUI?> = _customUi

    private val _customLogo = MutableStateFlow<String?>(null)
    val customLogo: StateFlow<String?> = _customLogo

    private val _exception = MutableStateFlow<String?>(null)
    val exception: StateFlow<String?> = _exception

    fun fetchCustomUi(url: String) {
        viewModelScope.launch {
            when (val response = customUiNetworkManager.fetchCustomUI(url)) {
                is ApiResult.Success -> _customUi.value = response.data
                is ApiResult.Error -> _exception.value = response.exception.toString()
                else -> _exception.value = COMMON_ERROR_MESSAGE
            }
        }
    }

    fun fetchCustomLogo(url: String) {
        viewModelScope.launch {
            when (val response = customUiNetworkManager.fetchLogo(url = url)) {
                is ApiResult.Success -> _customLogo.value = response.data
                is ApiResult.Error -> _exception.value = response.exception.toString()
                else -> _exception.value = COMMON_ERROR_MESSAGE
            }
        }
    }
}