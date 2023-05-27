package com.example.pk_device_android.presentation.remove_device

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.example.pk_device_android.presentation.mappers.Device
import com.example.pk_device_android.databinding.RemoveDeviceDialogBinding


class RemoveDeviceDialogFragment : DialogFragment() {

    private var _binding: RemoveDeviceDialogBinding? = null
    private val binding get() = _binding!!
    private val args: RemoveDeviceDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RemoveDeviceDialogBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            tvRemoveDevice.setOnClickListener {
                setFragmentResult(
                    REQUEST_KEY_REMOVE_DEVICE_ID,
                    bundleOf(BUNDLE_KEY_REMOVE_DEVICE_ID to args.devicePosition)
                )
                dismissAllowingStateLoss()
            }
            tvCancelRemoveDeviceDialog.setOnClickListener { dismissAllowingStateLoss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUEST_KEY_REMOVE_DEVICE_ID = "REQUEST_KEY_REMOVE_DEVICE_ID"
        private const val BUNDLE_KEY_REMOVE_DEVICE_ID = "BUNDLE_KEY_REMOVE_DEVICE_ID"
    }

}