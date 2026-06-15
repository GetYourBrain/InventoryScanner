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

            ScannerMode.BROADCAST -> setBroadcastMode()

            ScannerMode.KEYSTROKE -> setKeystrokeMode()

            ScannerMode.EDIT_TEXT -> setEditTextMode()
        }
    }

    override fun getCurrentMode(): ScannerMode? {
        return currentMode
    }

    private fun configureScanner(outputMode: Int){
        scanner.setOutputMode(outputMode)
        scanner.addPrefix("?")
        scanner.addSuffix("=")

        scanner.setTimeOut(3000)

        scanner.setIntervalTime(100)

        scanner.enablePlayBeep(false)
        scanner.enablePlayVibrate(false)

        scanner.lightSet(true)

        scanner.enableAddKeyValue(0)

        scanner.setEncodeFormart(4)

    }

    private fun setBroadcastMode() {
        configureScanner(1)
    }

    private fun setKeystrokeMode() {
        configureScanner(2)
    }

    private fun setEditTextMode() {
        configureScanner(0)
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