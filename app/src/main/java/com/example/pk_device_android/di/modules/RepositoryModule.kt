package com.example.pk_device_android.di.modules

import com.example.pk_device_android.data.repositories.DeviceRepositoryImpl
import com.example.pk_device_android.domain.interfaces.DeviceRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DeviceRepository> {
        DeviceRepositoryImpl(get())
    }
}