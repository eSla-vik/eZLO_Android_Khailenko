package com.example.pk_device_android.data.network.api

import com.example.pk_device_android.data.models.DevicesListResponse
import retrofit2.http.GET

interface DevicesApi {

    @GET("test_android/items.test")
    suspend fun getDeviceList() : DevicesListResponse

}