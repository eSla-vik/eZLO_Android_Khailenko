package com.example.pk_device_android.presentation.remove_device

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.pk_device_android.data.models.Device
import com.example.pk_device_android.databinding.RemoveDeviceDialogBinding


class RemoveDeviceDialogFragment : DialogFragment() {

    private var _binding: RemoveDeviceDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var onDeleteDeviceCallback: ((Device) -> Unit)
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
                onDeleteDeviceCallback(args.deleteDetailDevice)
                dismissAllowingStateLoss()
            }
            tvCancelRemoveDeviceDialog.setOnClickListener { dismissAllowingStateLoss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}