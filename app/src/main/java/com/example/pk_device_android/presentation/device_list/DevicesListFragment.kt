package com.example.pk_device_android.presentation.device_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pk_device_android.R
import com.example.pk_device_android.data.models.Device
import com.example.pk_device_android.databinding.DevicesListFragmentBinding
import com.example.pk_device_android.presentation.adapters.DevicesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DevicesListFragment : Fragment() {

    private var _binding: DevicesListFragmentBinding? = null
    private val binding get() = _binding!!
    private val deviceListViewModel by viewModel<DevicesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DevicesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        deviceListViewModel.deviceLiveData.observe(viewLifecycleOwner) {
            initAdapter(it)
        }
    }

    private fun initAdapter(devicesList: List<Device>) {
        binding.rvDevicesList.adapter = DevicesAdapter(
            { deviceDetail, isEditMode ->
                navigateToDetailDeviceScreen(deviceDetail, isEditMode)
            },
            { deviceDetail ->
                showDialogFragment(deviceDetail)
            },
            devicesList
        )
        addDividerToAdapter()
    }

    private fun addDividerToAdapter() {
        val decorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.item_divider)
            ?.let { decorator.setDrawable(it) }
        binding.rvDevicesList.addItemDecoration(decorator)
    }

    private fun navigateToDetailDeviceScreen(detailDevice: Device, isEditMode: Boolean) {
        val action = DevicesListFragmentDirections.devicesListFragmentToDetailDeviceFragment(
            detailDevice,
            isEditMode
        )
        findNavController().navigate(action)
    }

    private fun showDialogFragment(detailDevice: Device) {
        val action = DevicesListFragmentDirections.devicesListFragmentToDeleteDeviceDialog(detailDevice)
        findNavController().navigate(action)
    }

}