package com.example.pk_device_android.presentation.device_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pk_device_android.data.models.DevicesListResponse
import com.example.pk_device_android.data.models.DevicesResponse
import com.example.pk_device_android.domain.interactors.DeviceInteractor
import kotlinx.coroutines.launch

class DevicesListViewModel(
    private val deviceInteractor: DeviceInteractor
): ViewModel() {

    private var _deviceLiveData = MutableLiveData<DevicesListResponse>()
    val deviceLiveData: LiveData<DevicesListResponse> = _deviceLiveData

    init {
        getDeviceList()
    }

    fun getDeviceList() {
        viewModelScope.launch {
            _deviceLiveData.value = deviceInteractor.getDeviceList()
            Log.d("Khailenko", "test _deviceLiveData-> ${_deviceLiveData.value} ")
        }
    }

}