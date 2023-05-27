package com.example.pk_device_android.presentation.mappers

import com.example.pk_device_android.data.models.Device
import com.example.pk_device_android.data.models.DevicesListResponse

class DeviceMapper : Mapper<DevicesListResponse, Device> {
    override fun map(input: DevicesListResponse): List<Device> {
        val test = input.devices.map {
            Device(
                templateTitle = "test + ${input.devices.indexOf(it)+1}",
                pkDevice = it.pkDevice,
                macAddress = it.macAddress,
                imageSource = checkDevicePlatform(it.platform),
                firmware = it.firmware,
                platform = it.platform)
        }
        return test
    }

    private fun checkDevicePlatform(platform: String): Int {
        return when (platform) {
            DevicePlatform.SERCOMM_G450.name -> 1
            DevicePlatform.SERCOMM_G550.name -> 2
            DevicePlatform.MICASAVERDE_VERALITE.name -> 3
            DevicePlatform.SERCOMM_NA900.name -> 4
            DevicePlatform.SERCOMM_NA301.name -> 5
            DevicePlatform.SERCOMM_NA930.name -> 6
            else -> 8
        }
    }

}