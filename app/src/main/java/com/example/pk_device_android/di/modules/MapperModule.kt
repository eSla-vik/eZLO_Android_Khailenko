package com.example.pk_device_android.di.modules

import com.example.pk_device_android.presentation.mappers.DeviceMapper
import org.koin.dsl.module

val mapperModule = module {
    single {
        DeviceMapper()
    }
}