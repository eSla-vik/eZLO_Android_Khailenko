package com.example.pk_device_android.presentation.device_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pk_device_android.presentation.mappers.Device

class DetailDeviceViewModel : ViewModel() {

    private var _deviceUpdate = MutableLiveData<Device>()
    val deviceUpdate: LiveData<Device> = _deviceUpdate

    fun updateDeviceTitle(device: Device, newDeviceTitle: String) {
        val deviceWithNewTitle = device.copy(templateTitle = newDeviceTitle)
        _deviceUpdate.value = deviceWithNewTitle
    }

}