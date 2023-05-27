package com.example.pk_device_android.data.repositories

import com.example.pk_device_android.data.models.DevicesListResponse
import com.example.pk_device_android.data.network.api.DevicesApi

interface DeviceRepository {
    suspend fun getDeviceList(): DevicesListResponse
}

class DeviceRepositoryImpl(
    private val devicesApi: DevicesApi
) : DeviceRepository {
    override suspend fun getDeviceList(): DevicesListResponse {
        return devicesApi.getDeviceList()
    }
}