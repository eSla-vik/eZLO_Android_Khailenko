package com.example.pk_device_android.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    val templateTitle: String,
    val pkDevice: String,
    val macAddress: String,
    val imageSource: Int,
    val firmware: String,
    val platform: String
) : Parcelable