package com.example.pk_device_android.presentation.device_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pk_device_android.R
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.databinding.DevicesListFragmentBinding
import com.example.pk_device_android.presentation.adapters.DevicesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DevicesListFragment : Fragment() {

    private var _binding: DevicesListFragmentBinding? = null
    private val binding get() = _binding!!
    private val deviceListViewModel by viewModel<DevicesListViewModel>()
    private var adapter: DevicesAdapter? = null

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
        initResultListener()
    }

    private fun initAdapter(devicesList: List<Device>) {
        adapter = DevicesAdapter(
            { deviceDetail, isEditMode ->
                navigateToDetailDeviceScreen(deviceDetail, isEditMode)
            },
            { position ->
                showDialogFragment(position)
            },
            devicesList.toMutableList()
        )
        addDividerToAdapter()
        binding.rvDevicesList.adapter = adapter
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

    private fun showDialogFragment(position: Int) {
        val action = DevicesListFragmentDirections.devicesListFragmentToDeleteDeviceDialog(position)
        findNavController().navigate(action)
    }

    private fun initResultListener() {
        setFragmentResultListener(REQUEST_KEY_REMOVE_DEVICE_ID) { _, bundle ->
            val result = bundle.getInt(BUNDLE_KEY_REMOVE_DEVICE_ID)
            adapter?.deleteDevice(result)
            adapter?.let { deviceListViewModel.updateDeviceList(it.currentDeviceList) }
        }
    }

    private fun initObservers() {
        deviceListViewModel.deviceLiveData.observe(viewLifecycleOwner) {
            initAdapter(it)
        }
    }

    companion object {
        private const val REQUEST_KEY_REMOVE_DEVICE_ID = "REQUEST_KEY_REMOVE_DEVICE_ID"
        private const val BUNDLE_KEY_REMOVE_DEVICE_ID = "BUNDLE_KEY_REMOVE_DEVICE_ID"
    }

}