package com.example.pk_device_android.presentation.device_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pk_device_android.TestDispatcherRule
import com.example.pk_device_android.domain.interactors.DeviceInteractor
import com.example.pk_device_android.domain.interfaces.DeviceItem
import com.example.pk_device_android.presentation.device_list.DevicesListViewModel
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.presentation.mappers.DeviceMapper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import com.example.pk_device_android.R

@OptIn(ExperimentalCoroutinesApi::class)
class DevicesListViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val deviceInteractor: DeviceInteractor = mock()
    private val deviceMapper: DeviceMapper = mock()
    private val deviceItem = DeviceItem(
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
        platform = "platform"
    )
    private val device = Device(
        templateTitle = "Home Number 1",
        pkDevice = "SN 100",
        macAddress = "MAC Address: 01:02:03",
        imageSource = R.drawable.vera_edge_big,
        firmware = "Firmware: firmware",
        platform = "platform",
        model = "model"
    )

    @Test
    fun `test fetch devices`() = runTest {
        whenever(deviceInteractor.getDeviceList()).thenReturn(listOf(deviceItem))
        whenever(deviceMapper.map(listOf(deviceItem))).thenReturn(listOf(device))
        val viewModel = getViewModel()

        assertEquals(viewModel.deviceLiveData.value!!, listOf(device))
    }

    @Test
    fun `test remove devices`() = runTest {
        whenever(deviceInteractor.getDeviceList()).thenReturn(listOf(deviceItem))
        whenever(deviceMapper.map(listOf(deviceItem))).thenReturn(listOf(device))
        val viewModel = getViewModel()

        viewModel.removeDevice(device)

        assertTrue(viewModel.deviceLiveData.value!!.isEmpty())
    }

    private fun getViewModel(): DevicesListViewModel {
        return DevicesListViewModel(deviceInteractor, deviceMapper)
    }
}