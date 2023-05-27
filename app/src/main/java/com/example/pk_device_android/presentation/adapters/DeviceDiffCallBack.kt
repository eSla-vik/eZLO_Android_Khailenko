package com.example.pk_device_android.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.pk_device_android.presentation.mappers.Device

class DeviceDiffCallBack : DiffUtil.ItemCallback<Device>() {
    override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean =
        oldItem.pkDevice == newItem.pkDevice

    override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean =
        oldItem == newItem
}