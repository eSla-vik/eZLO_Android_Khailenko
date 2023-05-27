package com.example.pk_device_android.di.modules

import com.example.pk_device_android.presentation.StringResourceProvider
import com.example.pk_device_android.presentation.StringResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val resourceProviderModule = module {
    single<StringResourceProvider> {
        StringResourceProviderImpl(androidContext())
    }
}