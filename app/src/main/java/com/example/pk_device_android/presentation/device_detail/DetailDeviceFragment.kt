package com.example.pk_device_android.presentation.device_detail

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.databinding.DetailDeviceFragmentBinding
import com.example.pk_device_android.presentation.device_list.DevicesListFragment


class DetailDeviceFragment : Fragment() {

    private var _binding: DetailDeviceFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: DetailDeviceFragmentArgs by navArgs()
    private val detailDeviceViewModel: DetailDeviceViewModel by viewModels()

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
        initObserver()
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
                binding.acbSaveNewTitle.setOnClickListener {
                    detailDeviceViewModel.updateDeviceTitle(args.detailDeviceData, binding.acetDetailScreenDeviceName.text.toString())
                }
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

    private fun initObserver() {
        detailDeviceViewModel.deviceUpdate.observe(viewLifecycleOwner) {
            setFragmentResult(
                DevicesListFragment.REQUEST_KEY_CHANGE_DEVICE_TITLE,
                bundleOf(DevicesListFragment.BUNDLE_KEY_CHANGE_DEVICE_TITLE to it)
            )
            findNavController().popBackStack()
        }
    }

}