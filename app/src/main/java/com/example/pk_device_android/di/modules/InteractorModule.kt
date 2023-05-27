package com.example.pk_device_android.di.modules

import com.example.pk_device_android.domain.interactors.DeviceInteractor
import com.example.pk_device_android.domain.interactors.DeviceInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<DeviceInteractor> {
        DeviceInteractorImpl(get())
    }
}