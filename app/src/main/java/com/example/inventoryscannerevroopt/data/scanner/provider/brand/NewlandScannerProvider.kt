package com.example.inventoryscannerevroopt.data.scanner.provider.brand

import android.content.Intent
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.data.scanner.ScannerReceiver
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProvider
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

class NewlandScannerProvider(
    private val context: Context,
    private val onBarcodeScanned: (BarcodeData) -> Unit
): ScannerProvider {

    private val scannerReceiver = ScannerReceiver(onBarcodeScanned)
    override fun startScanning() {
       val intent = Intent("nlscan.action.SCANNER_TRIG")
        context.sendBroadcast(intent)
    }

    override fun stopScanning() {
        val intent = Intent("nlscan.action.STOP_SCAN")
        context.sendBroadcast(intent)
    }

    /*override val supportedModes = setOf(
        ScannerMode.KEYBOARD,
        ScannerMode.BROADCAST
    )*/

    override fun registerReceiver() {
        registerScannerReceiver()
    }

    override fun unregisterReceiver() {
        context.unregisterReceiver(
            scannerReceiver
        )
    }

    private fun registerScannerReceiver(){
        val filter = IntentFilter("nlscan.action.SCANNER_RESULT")
        ContextCompat.registerReceiver(
            context,
            scannerReceiver,
            filter,
            ContextCompat.RECEIVER_EXPORTED
        )
    }

}