package com.example.pk_device_android.data.models

import com.google.gson.annotations.SerializedName

data class DevicesListResponse(
    @SerializedName("Devices")
    val devices: List<DevicesResponse>
)