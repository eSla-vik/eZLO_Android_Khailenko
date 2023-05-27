package com.example.pk_device_android.presentation.mappers

import android.content.Context
import android.util.Log
import com.example.pk_device_android.R
import com.example.pk_device_android.data.models.Device
import com.example.pk_device_android.data.models.DevicesListResponse
import com.example.pk_device_android.presentation.StringResourceProvider

interface DeviceMapper {
    fun map(input: DevicesListResponse): List<Device>
}

class DeviceMapperImpl(
    private val stringResourceProvider: StringResourceProvider
) : DeviceMapper {
    override fun map(input: DevicesListResponse): List<Device> {
        val mapperDeviceList = input.devices.map {
            Device(
                templateTitle = stringResourceProvider.getString(
                    R.string.device_detail_template,
                    stringResourceProvider.getString(R.string.device_home_number_template),
                    (input.devices.indexOf(it) + 1).toString()
                ),
                pkDevice = stringResourceProvider.getString(
                    R.string.device_detail_template,
                    stringResourceProvider.getString(R.string.device_sn_template),
                    (it.pkDevice).toString()
                ),
                macAddress = stringResourceProvider.getString(
                    R.string.device_detail_template,
                    stringResourceProvider.getString(R.string.device_mac_address_template),
                    (it.macAddress)
                ),
                imageSource = checkDevicePlatform(it.platform),
                firmware = stringResourceProvider.getString(
                    R.string.device_detail_template,
                    stringResourceProvider.getString(R.string.device_firmwave_template),
                    (it.firmware)
                ),
                platform = it.platform
            )
        }
        return mapperDeviceList
    }

    private fun checkDevicePlatform(platform: String): Int {
        return when (platform) {
            DevicePlatform.SERCOMM_G450.type -> R.drawable.vera_plus_big
            DevicePlatform.SERCOMM_G550.type -> R.drawable.vera_secure_big
            DevicePlatform.MICASAVERDE_VERALITE.type -> R.drawable.vera_edge_big
            DevicePlatform.SERCOMM_NA900.type -> R.drawable.vera_edge_big
            DevicePlatform.SERCOMM_NA301.type -> R.drawable.vera_edge_big
            DevicePlatform.SERCOMM_NA930.type -> R.drawable.vera_edge_big
            else -> R.drawable.vera_edge_big
        }
    }

}