package com.example.pk_device_android.presentation.mappers

import com.example.pk_device_android.domain.interfaces.DeviceItem
import com.example.pk_device_android.presentation.StringResourceProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import com.example.pk_device_android.R

class DeviceMapperTest {

    private val stringResourceProvider: StringResourceProvider = mock()

    @Test
    fun `test map DeviceItem to Device`() {
        val listDeviceItem = listOf(
            DeviceItem(
                pkDevice = 100,
                macAddress = "01:02:03",
                pkDeviceType = 200,
                pkDeviceSubType = 300,
                firmware = "firmware",
                serverDevice = "device",
                serverEvent = "event",
                serverAccount = "account",
                internalIP = "100.100.100.100",
                lastAliveReported = "alive",
                platform = DevicePlatform.SERCOMM_G450.type
            )
        )
        whenever(
            stringResourceProvider.getString(
                R.string.device_home_number_template,
                "1"
            )
        ).thenReturn("Home Number 1")
        whenever(
            stringResourceProvider.getString(
                R.string.device_sn_template,
                "100"
            )
        ).thenReturn("SN 100")
        whenever(
            stringResourceProvider.getString(
                R.string.device_mac_address_template,
                "01:02:03"
            )
        ).thenReturn("MAC Address: 01:02:03")
        whenever(
            stringResourceProvider.getString(
                R.string.device_firmwave_template,
                "firmware"
            )
        ).thenReturn("Firmware: firmware")
        whenever(
            stringResourceProvider.getString(R.string.vera_plus)
        ).thenReturn("Vera Plus")
        whenever(
            stringResourceProvider.getString(
                R.string.device_model_template,
                "Vera Plus"
            )
        ).thenReturn("Model: Vera Plus")
        val mapper = getMapper()

        val mapList = mapper.map(listDeviceItem)
        val device = mapList[0]

        assertEquals(device.templateTitle, "Home Number 1")
        assertEquals(device.pkDevice, "SN 100")
        assertEquals(device.macAddress, "MAC Address: 01:02:03")
        assertEquals(device.firmware, "Firmware: firmware")
        assertEquals(device.imageSource, R.drawable.vera_plus_big)
        assertEquals(device.platform, "Sercomm G450")
        assertEquals(device.model, "Model: Vera Plus")
    }

    private fun getMapper(): DeviceMapper {
        return DeviceMapperImpl(stringResourceProvider)
    }
}