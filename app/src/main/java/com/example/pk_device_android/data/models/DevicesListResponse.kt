package com.example.pk_device_android.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DevicesListResponse(
    @SerializedName("Devices")
    val devices: List<DevicesResponse>
) : Parcelable