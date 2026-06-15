package com.example.inventoryscannerevroopt.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import com.example.inventoryscannerevroopt.R
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import kotlinx.coroutines.launch

class ScannerFragment : Fragment(
    R.layout.fragment_scanner
) {

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

        val btnStart =
            view.findViewById<Button>(
                R.id.btnStartScan
            )

        val btnStop =
            view.findViewById<Button>(
                R.id.btnStopScan
            )

        val tvScanResult =
            view.findViewById<TextView>(
                R.id.tvScanResult
            )

        val tvCurrentMode =
            view.findViewById<TextView>(
                R.id.tvCurrentMode
            )

        when (viewModel.currentMode.value) {

            ScannerMode.BROADCAST ->
                view.findViewById<TextView>(
                    R.id.tvCurrentMode
                ).text =
                    "Выбран режим сканирования: Broadcast"

            ScannerMode.KEYSTROKE ->
                view.findViewById<TextView>(
                    R.id.tvCurrentMode
                ).text =
                    "Выбран режим сканирования: Keystroke"

            ScannerMode.EDIT_TEXT ->
                view.findViewById<TextView>(
                    R.id.tvCurrentMode
                ).text =
                    "Выбран режим сканирования: EDIT_TEXT"
        }

        btnStart.setOnClickListener {
            viewModel.startScan()
        }

        btnStop.setOnClickListener {
            viewModel.stopScan()
        }

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.barcode.collect {

                    tvScanResult.text = it
                }
            }
        }
    }
}