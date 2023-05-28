package com.example.pk_device_android.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pk_device_android.databinding.DeviceItemBinding
import com.example.pk_device_android.presentation.mappers.Device

class DevicesAdapter(
    private val detailDeviceClickCallback: ((Device, Boolean) -> Unit),
    private val detailDeviceLongClickCallback: ((Device) -> Unit)
) : ListAdapter<Device, DevicesAdapter.DevicesViewHolder>(DeviceDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        return DevicesViewHolder(
            DeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DevicesViewHolder(private val deviceItemBinding: DeviceItemBinding) :
        RecyclerView.ViewHolder(deviceItemBinding.root) {

        fun bind(item: Device) {
            with(deviceItemBinding) {
                Glide.with(itemView).load(item.imageSource).into(itemDeviceImage)
                itemDeviceTitle.text = item.templateTitle
                itemDevicePk.text = item.pkDevice
                itemDeviceDetailButton.setOnClickListener { detailDeviceClickCallback(item, false) }
                itemDeviceEditButton.setOnClickListener { detailDeviceClickCallback(item, true) }
                itemDeviceContainer.setOnLongClickListener { detailDeviceLongClickCallback(item)
                    true
                }
            }
        }
    }

}