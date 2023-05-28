package com.example.pk_device_android.presentation.device_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pk_device_android.domain.interactors.DeviceInteractor
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.presentation.mappers.DeviceMapper
import kotlinx.coroutines.launch

class DevicesListViewModel(
    private val deviceInteractor: DeviceInteractor,
    private val deviceMapper: DeviceMapper
): ViewModel() {

    private var _deviceLiveData = MutableLiveData<List<Device>>()
    val deviceLiveData: LiveData<List<Device>> = _deviceLiveData

    private var _exceptionHandlerLiveData = MutableLiveData<Boolean>()
    val exceptionHandlerLiveData: LiveData<Boolean> = _exceptionHandlerLiveData

    init {
        getDeviceList()
    }

    fun getDeviceList() {
        viewModelScope.launch {
            try {
                val listOfDevice = deviceInteractor.getDeviceList()
                val mapperListOfDevice = deviceMapper.map(listOfDevice)
                _deviceLiveData.value = mapperListOfDevice
                _exceptionHandlerLiveData.value = false
            } catch (e: Exception) {
                _exceptionHandlerLiveData.value = true
            }
        }
    }

    fun removeDevice(device: Device) {
        val list = _deviceLiveData.value.orEmpty().toMutableList()
        list.remove(device)
        _deviceLiveData.value = list
    }

    fun updateDevice(newDevice: Device) {
        val list = _deviceLiveData.value.orEmpty().toMutableList()
        list.forEachIndexed { index, device ->
            val deviceToUpdatedTitle = list.find { device.pkDevice == newDevice.pkDevice }
            if (deviceToUpdatedTitle != null) {
                val replaceDevice = device.copy(
                    templateTitle = newDevice.templateTitle
                )
                list[index] = replaceDevice
            }
        }
        _deviceLiveData.value = list
    }

}