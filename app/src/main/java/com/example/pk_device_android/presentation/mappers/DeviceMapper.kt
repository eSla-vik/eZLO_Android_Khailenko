package com.example.pk_device_android.presentation.mappers

import com.example.pk_device_android.R
import com.example.pk_device_android.domain.interfaces.DeviceItem
import com.example.pk_device_android.presentation.StringResourceProvider

interface DeviceMapper {
    fun map(deviceItemList: List<DeviceItem>): List<Device>
}

class DeviceMapperImpl(
    private val stringResourceProvider: StringResourceProvider
) : DeviceMapper {
    override fun map(deviceItemList: List<DeviceItem>): List<Device> {
        val mapperDeviceList = deviceItemList.map {
            Device(
                templateTitle = stringResourceProvider.getString(
                    R.string.device_home_number_template,
                    (deviceItemList.indexOf(it) + 1).toString()
                ),
                pkDevice = stringResourceProvider.getString(
                    R.string.device_sn_template,
                    (it.pkDevice).toString()
                ),
                macAddress = stringResourceProvider.getString(
                    R.string.device_mac_address_template,
                    (it.macAddress)
                ),
                imageSource = checkDevicePlatform(it.platform),
                firmware = stringResourceProvider.getString(
                    R.string.device_firmwave_template,
                    (it.firmware)
                ),
                platform = it.platform,
                model = stringResourceProvider.getString(
                    R.string.device_model_template,
                    setDeviceModel(it.platform)
                )
            )
        }
        return mapperDeviceList
    }

    private fun checkDevicePlatform(platform: String): Int {
        return when (platform) {
            DevicePlatform.SERCOMM_G450.type -> R.drawable.vera_plus_big
            DevicePlatform.SERCOMM_G550.type -> R.drawable.vera_secure_big
            else -> R.drawable.vera_edge_big
        }
    }

    private fun setDeviceModel(platform: String): String {
        return when (platform) {
            DevicePlatform.SERCOMM_G450.type -> stringResourceProvider.getString(R.string.vera_plus)
            DevicePlatform.SERCOMM_G550.type -> stringResourceProvider.getString(R.string.vera_secure)
            else -> stringResourceProvider.getString(R.string.vera_edge)
        }
    }

}