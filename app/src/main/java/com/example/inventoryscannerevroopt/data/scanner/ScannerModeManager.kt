package com.example.inventoryscannerevroopt.data.scanner

import android.content.Context
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProviderFactory

class ScannerModeManager(context: Context): ScannerModeController {
    private val controller: ScannerModeController
    init {
        val deviceInfo = DeviceInfoProvider().getDeviceInfo()
        val scannerType = ScannerTypeResolver().resolve(
            deviceInfo
        )

        val provider = ScannerProviderFactory.createScanner(
            type = scannerType,
            context = context,
            onBarcodeScanned = {}
        )

        controller = provider as ScannerModeController
    }

    val deviceInfo = DeviceInfoProvider().getDeviceInfo()

    override val supportedModes: Set<ScannerMode>
        get() = controller.supportedModes

    override fun setMode(mode: ScannerMode) {
        controller.setMode(mode)
    }

    override fun getCurrentMode(): ScannerMode? {
        return controller.getCurrentMode()
    }
}