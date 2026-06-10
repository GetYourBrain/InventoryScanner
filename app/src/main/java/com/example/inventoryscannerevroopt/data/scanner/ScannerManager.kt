package com.example.inventoryscannerevroopt.data.scanner

import android.content.Context
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProvider
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProviderFactory
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

class ScannerManager(
    context: Context,
    onBarcodeScanned: (BarcodeData) -> Unit
) {
    private val scannerProvider : ScannerProvider

    init {

        val deviceInfo = DeviceInfoProvider().getDeviceInfo()

        val scannerType = ScannerTypeResolver().resolve(deviceInfo)

        scannerProvider = ScannerProviderFactory.createScanner(
            type = scannerType,
            context = context,
            onBarcodeScanned = onBarcodeScanned
        )
    }

    fun startScanning(){
        scannerProvider.startScanning()
    }

    fun stopScanning(){
        scannerProvider.stopScanning()
    }

    fun registerReceiver(){
        scannerProvider.registerReceiver()
    }

    fun unregisterReceiver(){
        scannerProvider.unregisterReceiver()
    }


}