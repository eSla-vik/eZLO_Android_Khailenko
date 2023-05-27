package com.example.pk_device_android.domain.interfaces

import com.example.pk_device_android.data.models.DevicesListResponse

data class DeviceItem(
    val pkDevice: Int,
    val macAddress: String,
    val pkDeviceType: Int,
    val pkDeviceSubType: Int,
    val firmware: String,
    val serverDevice: String,
    val serverEvent: String,
    val serverAccount: String,
    val internalIP: String,
    val lastAliveReported: String,
    val platform: String,
)

fun DevicesListResponse.toDevicesList(): List<DeviceItem> {
    return devices.map {
        DeviceItem(
            pkDevice = it.pkDevice,
            macAddress = it.macAddress,
            pkDeviceType = it.pkDeviceType,
            pkDeviceSubType = it.pkDeviceSubType,
            firmware = it.firmware,
            serverDevice = it.serverDevice,
            serverEvent = it.serverEvent,
            serverAccount = it.serverAccount,
            internalIP = it.internalIP,
            lastAliveReported = it.lastAliveReported,
            platform = it.platform
        )
    }
}