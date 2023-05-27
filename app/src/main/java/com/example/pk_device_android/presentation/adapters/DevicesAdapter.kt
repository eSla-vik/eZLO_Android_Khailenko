package com.example.pk_device_android.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pk_device_android.data.models.DevicesResponse
import com.example.pk_device_android.databinding.DeviceItemBinding

class DevicesAdapter(
    private val detailDeviceClickCallback: ((DevicesResponse, Boolean) -> Unit),
    private val detailDeviceLongClickCallback: ((DevicesResponse) -> Unit),
    private val devicesList: List<DevicesResponse>
) : RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        return DevicesViewHolder(
            DeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = devicesList.size

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.bind(devicesList[position])
    }

    inner class DevicesViewHolder(private val deviceItemBinding: DeviceItemBinding) :
        RecyclerView.ViewHolder(deviceItemBinding.root) {

        fun bind(item: DevicesResponse) {
            with(deviceItemBinding) {
                acivTvDeviceItemTitle.text = item.pkDevice.toString()
                acibDeviceItemDetailBtn.setOnClickListener {
                    detailDeviceClickCallback(
                        item,
                        false
                    )
                }
                acibDeviceItemEditBtn.setOnClickListener { detailDeviceClickCallback(item, true) }
                clMainContainerDeviceItem.setOnLongClickListener {
                    detailDeviceLongClickCallback(item)
                    true
                }
            }
        }
    }

}