package com.example.pk_device_android.domain.interactors

import com.example.pk_device_android.domain.interfaces.DeviceItem
import com.example.pk_device_android.domain.interfaces.DeviceRepository

interface DeviceInteractor {
    suspend fun getDeviceList(): List<DeviceItem>
}

class DeviceInteractorImpl(
    private val deviceRepository: DeviceRepository
) : DeviceInteractor {

    override suspend fun getDeviceList(): List<DeviceItem> {
        return deviceRepository.getDeviceList()

    }

}