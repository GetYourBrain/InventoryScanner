package com.example.inventoryscannerevroopt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.inventoryscannerevroopt.data.scanner.ScannerManager
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

class ScannerViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val scannerManager = ScannerManager(
        application
    ) { barcodeData: BarcodeData ->

        _barcode.value = barcodeData.barcode
    }

    private val _barcode = MutableStateFlow("Нет данных")
    val barcode: StateFlow<String> = _barcode

    val supportedModes = scannerManager.supportedModes

    private val _currentMode = MutableStateFlow(
        scannerManager.getCurrentMode()
            ?: ScannerMode.BROADCAST
    )

    val currentMode: StateFlow<ScannerMode> =
        _currentMode

    fun startScan() {
        scannerManager.startScanning()
    }

    fun stopScan() {
        scannerManager.stopScanning()
    }

    fun registerReceiver() {
        scannerManager.registerReceiver()
    }

    fun unregisterReceiver() {
        scannerManager.unregisterReceiver()
    }

    fun setMode(mode: ScannerMode) {
        scannerManager.setMode(mode)
        _currentMode.value = mode
    }
}