package com.example.inventoryscannerevroopt.data.scanner

import android.content.Context
import android.util.Log
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProvider
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProviderFactory
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

class ScannerManager(
    context: Context,
    onBarcodeScanned: (BarcodeData) -> Unit
) : ScannerModeController {

    private val scannerProvider: ScannerProvider

    private val modeController: ScannerModeController?

    init {

        val deviceInfo = DeviceInfoProvider().getDeviceInfo()

        val scannerType = ScannerTypeResolver().resolve(deviceInfo)

        Log.d("SCAN_TEST", "Scanner type = $scannerType")

        scannerProvider = ScannerProviderFactory.createScanner(
            type = scannerType,
            context = context,
            onBarcodeScanned = onBarcodeScanned
        )

        modeController = scannerProvider as? ScannerModeController
    }

    override val supportedModes: Set<ScannerMode>
        get() = modeController?.supportedModes ?: emptySet()

    override fun setMode(mode: ScannerMode) {
        modeController?.setMode(mode)
    }

    override fun getCurrentMode(): ScannerMode? {
        return modeController?.getCurrentMode()
    }

    fun startScanning() {
        Log.d("SCAN_TEST", "ScannerManager.startScanning")
        scannerProvider.startScanning()
    }

    fun stopScanning() {
        scannerProvider.stopScanning()
    }

    fun registerReceiver() {
        scannerProvider.registerReceiver()
    }

    fun unregisterReceiver() {
        scannerProvider.unregisterReceiver()
    }
}