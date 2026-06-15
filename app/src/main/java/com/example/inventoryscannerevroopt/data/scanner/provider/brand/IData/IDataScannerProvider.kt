package com.example.inventoryscannerevroopt.data.scanner.provider.brand.IData

import android.content.Context
import android.util.Log
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.data.scanner.ScannerModeController
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProvider
import com.example.inventoryscannerevroopt.domain.model.BarcodeData
import com.example.iscandemo.iScanInterface

class IDataScannerProvider(
    private val context: Context,
    private val onBarcodeScanned: (BarcodeData) -> Unit
): ScannerProvider, ScannerModeController {

    private val scanner = iScanInterface(context)

    private val listener = IDataScanListener(onBarcodeScanned)

    private var currentMode: ScannerMode? = null

    override val supportedModes = setOf(
        ScannerMode.BROADCAST,
        ScannerMode.KEYSTROKE,
        ScannerMode.EDIT_TEXT
    )

    override fun setMode(mode: ScannerMode) {

        currentMode = mode

        when (mode) {

            ScannerMode.BROADCAST ->
                scanner.setOutputMode(1)

            ScannerMode.KEYSTROKE ->
                scanner.setOutputMode(2)

            ScannerMode.EDIT_TEXT ->
                scanner.setOutputMode(0)
        }
    }

    override fun getCurrentMode(): ScannerMode? {
        return currentMode
    }

    override fun startScanning() {
        Log.d("SCAN_TEST", "Provider startScanning")
        scanner.scan_start()
    }

    override fun stopScanning() {
        Log.d("SCAN_TEST", "Provider stopScanning")
        scanner.scan_stop()

    }

    override fun registerReceiver() {
        Log.d("SCAN_TEST", "Provider registerReceiver")
        scanner.registerScan(listener)
    }

    override fun unregisterReceiver() {
        Log.d("SCAN_TEST", "Provider unregisterReceiver")
        scanner.unregisterScan(listener)
    }
}