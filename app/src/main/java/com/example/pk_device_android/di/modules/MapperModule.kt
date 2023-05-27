package com.example.pk_device_android.di.modules

import com.example.pk_device_android.presentation.mappers.DeviceMapper
import com.example.pk_device_android.presentation.mappers.DeviceMapperImpl
import org.koin.dsl.module

val mapperModule = module {
    single<DeviceMapper> {
        DeviceMapperImpl(get())
    }
}