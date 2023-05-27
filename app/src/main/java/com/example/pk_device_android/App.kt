package com.example.pk_device_android

import android.app.Application
import com.example.pk_device_android.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, repositoryModule, interactorModule, viewModelModule, mapperModule)
      }
    }

}