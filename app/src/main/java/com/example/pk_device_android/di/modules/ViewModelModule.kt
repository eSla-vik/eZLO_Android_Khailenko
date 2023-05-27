package com.example.pk_device_android.di.modules

import com.example.pk_device_android.presentation.device_list.DevicesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DevicesListViewModel(get(), get())
    }
}
