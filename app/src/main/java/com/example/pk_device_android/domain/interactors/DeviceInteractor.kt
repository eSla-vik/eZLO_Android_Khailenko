package com.example.pk_device_android.domain.interactors

import com.example.pk_device_android.data.models.DevicesListResponse
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