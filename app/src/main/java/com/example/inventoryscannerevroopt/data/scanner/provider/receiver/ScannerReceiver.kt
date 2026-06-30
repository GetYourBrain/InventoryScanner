package com.example.inventoryscannerevroopt.data.scanner.provider.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.inventoryscannerevroopt.domain.model.BarcodeData
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class ScannerReceiver(
    private val onBarcodeScanned: (BarcodeData) -> Unit
): BroadcastReceiver() {

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
       Log.d("SCAN_TEST", "Broadcast received")
       val barcodeData = extractBarcodeData(intent)
        if (barcodeData != null){
            Log.d("SCAN_TEST", "Receiver barcode = ${barcodeData.barcode}")
            onBarcodeScanned(barcodeData)
        }
    }

    protected abstract fun extractBarcodeData(intent: Intent?): BarcodeData?


}