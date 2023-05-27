package com.example.pk_device_android.di.modules

import com.example.pk_device_android.data.repositories.DeviceRepository
import com.example.pk_device_android.data.repositories.DeviceRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<DeviceRepository> {
        DeviceRepositoryImpl(get())
    }
}