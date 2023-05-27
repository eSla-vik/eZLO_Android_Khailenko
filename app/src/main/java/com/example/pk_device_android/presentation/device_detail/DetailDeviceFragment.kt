package com.example.pk_device_android.presentation.device_detail

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.databinding.DetailDeviceFragmentBinding


class DetailDeviceFragment : Fragment() {

    private var _binding: DetailDeviceFragmentBinding? = null
    private val binding get() = _binding!!
    val args: DetailDeviceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailDeviceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreenMode(args.isEditMode)
        initDeviceData(args.detailDeviceData)
    }

    private fun initScreenMode(isEditMode: Boolean) {
        with(binding) {
            acetDetailScreenDeviceName.isEnabled = isEditMode
            acbSaveNewTitle.isVisible = isEditMode
            if (isEditMode) {
                acetDetailScreenDeviceName.requestFocus()
                val inputMethodManager =
                    requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(
                    acetDetailScreenDeviceName,
                    InputMethodManager.SHOW_IMPLICIT
                )
            }
        }
    }
    private fun initDeviceData(device: Device) {
        with(binding) {
            acetDetailScreenDeviceName.setText(device.templateTitle)
            acivDetailScreenDeviceSn.text = device.pkDevice
            acivDetailScreenDeviceMacAddress.text = device.macAddress
            acivDetailScreenDeviceFirmware.text = device.firmware
            acivDetailScreenDeviceModel.text = device.firmware
            Glide.with(requireActivity()).load(device.imageSource).into(acivDetailScreenDeviceImage)
        }
    }

}