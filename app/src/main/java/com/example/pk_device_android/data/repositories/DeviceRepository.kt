package com.example.pk_device_android.data.repositories

import com.example.pk_device_android.data.network.api.DevicesApi
import com.example.pk_device_android.domain.interfaces.DeviceItem
import com.example.pk_device_android.domain.interfaces.DeviceRepository
import com.example.pk_device_android.domain.interfaces.toDevicesList

class DeviceRepositoryImpl(
    private val devicesApi: DevicesApi
) : DeviceRepository {
    override suspend fun getDeviceList(): List<DeviceItem> {
        return devicesApi.getDeviceList().toDevicesList()
    }
}