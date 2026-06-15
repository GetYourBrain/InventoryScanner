package com.example.inventoryscannerevroopt.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.inventoryscannerevroopt.R
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider

class SettingsFragment : Fragment(
    R.layout.fragment_settings
) {
    val provider = DeviceInfoProvider()
    val deviceInfo = provider.getDeviceInfo()

    private val viewModel: ScannerViewModel
            by activityViewModels {
                ScannerViewModelFactory(
                    requireActivity().application
                )
            }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

        view.findViewById<TextView>(
            R.id.tvBrand
        ).text = "Бренд: ${deviceInfo.brandName}"

        view.findViewById<TextView>(
            R.id.tvModel
        ).text = "Модель: ${deviceInfo.modelName}"

        view.findViewById<TextView>(
            R.id.tvAndroid
        ).text =
            "Android: ${deviceInfo.androidVersion}"

        val btnBroadcast =
            view.findViewById<Button>(
                R.id.btnBroadcast
            )

        val btnKeystroke =
            view.findViewById<Button>(
                R.id.btnKeystroke
            )

        val btnEditText =
            view.findViewById<Button>(
                R.id.btnEditText
            )

        btnBroadcast.setOnClickListener {
            viewModel.setMode(
                ScannerMode.BROADCAST
            )
        }

        btnKeystroke.setOnClickListener {
            viewModel.setMode(
                ScannerMode.KEYSTROKE
            )
        }

        btnEditText.setOnClickListener {
            viewModel.setMode(
                ScannerMode.EDIT_TEXT
            )
        }

        when (viewModel.currentMode.value) {

            ScannerMode.BROADCAST ->
                view.findViewById<TextView>(
                    R.id.tvMode
                ).text =
                    "Выбран режим сканирования: Broadcast"

            ScannerMode.KEYSTROKE ->
                view.findViewById<TextView>(
                    R.id.tvMode
                ).text =
                    "Выбран режим сканирования: Keystroke"

            ScannerMode.EDIT_TEXT ->
                view.findViewById<TextView>(
                    R.id.tvMode
                ).text =
                    "Выбран режим сканирования: EDIT_TEXT"
        }
    }
}