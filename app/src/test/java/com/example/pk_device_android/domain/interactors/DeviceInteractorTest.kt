package com.example.pk_device_android.domain.interactors

import com.example.pk_device_android.domain.interfaces.DeviceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class DeviceInteractorTest {

    private val deviceRepository: DeviceRepository = mock()

    @Test
    fun `when get devices, getDeviceList should be called`() = runTest {
        val interactor = getInteractor()

        interactor.getDeviceList()

        verify(deviceRepository).getDeviceList()
    }

    private fun getInteractor(): DeviceInteractor {
        return DeviceInteractorImpl(deviceRepository)
    }
}