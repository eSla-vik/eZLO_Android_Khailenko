package com.example.pk_device_android.presentation.device_list

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
    private var adapter = DevicesAdapter(
        { deviceDetail, isEditMode ->
            navigateToDetailDeviceScreen(deviceDetail, isEditMode)
        },
        { device ->
            showDialogFragment(device)
        },
    )

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
        initAdapter()
        initClickListeners()
    }

    private fun initAdapter() {
        val decorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.item_divider)
            ?.let { decorator.setDrawable(it) }
        binding.deviceListScreenRecyclerView.addItemDecoration(decorator)
        binding.deviceListScreenRecyclerView.adapter = adapter
    }

    private fun navigateToDetailDeviceScreen(detailDevice: Device, isEditMode: Boolean) {
        val action = DevicesListFragmentDirections.devicesListFragmentToDetailDeviceFragment(
            detailDevice,
            isEditMode
        )
        findNavController().navigate(action)
    }

    private fun showDialogFragment(device: Device) {
        val action = DevicesListFragmentDirections.devicesListFragmentToDeleteDeviceDialog(device)
        findNavController().navigate(action)
    }

    private fun initResultListener() {
        setFragmentResultListener(REQUEST_KEY_REMOVE_DEVICE) { _, bundle ->
            val device = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(BUNDLE_KEY_REMOVE_DEVICE, Device::class.java)
            } else {
                bundle.getParcelable(BUNDLE_KEY_REMOVE_DEVICE)
            }
            if (device != null) {
                deviceListViewModel.removeDevice(device)
            }
        }

        setFragmentResultListener(REQUEST_KEY_CHANGE_DEVICE_TITLE) { _, bundle ->
            val device = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(BUNDLE_KEY_CHANGE_DEVICE_TITLE, Device::class.java)
            } else {
                bundle.getParcelable(BUNDLE_KEY_CHANGE_DEVICE_TITLE)
            }
            if (device != null) {
                deviceListViewModel.updateDevice(device)
            }
        }
    }

    private fun initObservers() {
        deviceListViewModel.deviceLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        deviceListViewModel.exceptionHandlerLiveData.observe(viewLifecycleOwner) { isException ->
            binding.tryAgainButton.isVisible = isException
            if (isException) Toast.makeText(
                requireContext(),
                R.string.something_went_wrong,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initClickListeners() {
        binding.tryAgainButton.setOnClickListener {
            deviceListViewModel.getDeviceList()
        }
    }

    companion object {
        const val REQUEST_KEY_REMOVE_DEVICE = "REQUEST_KEY_REMOVE_DEVICE"
        const val BUNDLE_KEY_REMOVE_DEVICE = "BUNDLE_KEY_REMOVE_DEVICE"
        const val REQUEST_KEY_CHANGE_DEVICE_TITLE = "REQUEST_KEY_CHANGE_DEVICE_TITLE"
        const val BUNDLE_KEY_CHANGE_DEVICE_TITLE = "BUNDLE_KEY_CHANGE_DEVICE_TITLE"
    }

}