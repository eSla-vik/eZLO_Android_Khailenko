package com.example.pk_device_android.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pk_device_android.data.models.DevicesListResponse
import com.example.pk_device_android.data.models.DevicesResponse
import com.example.pk_device_android.data.repositories.DeviceRepository

interface DeviceInteractor {
    suspend fun getDeviceList(): DevicesListResponse
}

class DeviceInteractorImpl(
    private val deviceRepository: DeviceRepository
) : DeviceInteractor {

    override suspend fun getDeviceList(): DevicesListResponse {
        return deviceRepository.getDeviceList()

    }

}