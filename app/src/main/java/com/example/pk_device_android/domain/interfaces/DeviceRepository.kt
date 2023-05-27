package com.example.pk_device_android.domain.interfaces

interface DeviceRepository {
    suspend fun getDeviceList(): List<DeviceItem>
}