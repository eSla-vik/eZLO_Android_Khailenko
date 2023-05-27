package com.example.pk_device_android.presentation.device_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pk_device_android.data.models.DevicesResponse
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
        initScreenMode()
        initDeviceData(args.detailDeviceData)
        Log.d("Khailenko", "args bool -> ${args.isEditMode}")
        Log.d("Khailenko", "args data -> ${args.detailDeviceData}")
    }

    private fun initScreenMode() {

    }

    private fun initDeviceData(devicesResponse: DevicesResponse) {
        with(binding) {

        }
    }

}