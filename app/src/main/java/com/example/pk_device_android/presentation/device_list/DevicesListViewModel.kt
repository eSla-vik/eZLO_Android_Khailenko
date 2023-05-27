package com.example.pk_device_android.presentation.device_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.domain.interactors.DeviceInteractor
import com.example.pk_device_android.presentation.mappers.DeviceMapper
import kotlinx.coroutines.launch

class DevicesListViewModel(
    private val deviceInteractor: DeviceInteractor,
    private val deviceMapper: DeviceMapper
): ViewModel() {

    private var _deviceLiveData = MutableLiveData<List<Device>>()
    val deviceLiveData: LiveData<List<Device>> = _deviceLiveData

    init {
        getDeviceList()
    }

    private fun getDeviceList() {
        viewModelScope.launch {
            val listOfDevice = deviceInteractor.getDeviceList()
            val mapperListOfDevice = deviceMapper.map(listOfDevice)
            _deviceLiveData.value = mapperListOfDevice
        }
    }

    fun removeDevice(device: Device) {
        val list = _deviceLiveData.value.orEmpty().toMutableList()
        list.remove(device)
        _deviceLiveData.value = list
    }

}