package com.example.pk_device_android.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.databinding.DeviceItemBinding

class DevicesAdapter(
    private val detailDeviceClickCallback: ((Device, Boolean) -> Unit),
    private val detailDeviceLongClickCallback: ((Int) -> Unit),
    private val devicesList: MutableList<Device>
) : RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        return DevicesViewHolder(
            DeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    val currentDeviceList: List<Device> = devicesList

    override fun getItemCount(): Int = devicesList.size

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.bind(devicesList[position], position)
    }

    fun deleteDevice(position: Int) {
        devicesList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, devicesList.size)
        notifyDataSetChanged()
    }

    inner class DevicesViewHolder(private val deviceItemBinding: DeviceItemBinding) :
        RecyclerView.ViewHolder(deviceItemBinding.root) {

        fun bind(item: Device, position: Int) {
            with(deviceItemBinding) {
                Glide.with(itemView).load(item.imageSource).into(acivDeviceItemImage)
                acivTvDeviceItemTitle.text = item.templateTitle
                acivTvDeviceItemPk.text = item.pkDevice
                acibDeviceItemDetailBtn.setOnClickListener { detailDeviceClickCallback(item, false) }
                acibDeviceItemEditBtn.setOnClickListener { detailDeviceClickCallback(item, true) }
                clMainContainerDeviceItem.setOnLongClickListener { detailDeviceLongClickCallback(position)
                    true
                }
            }
        }
    }

}