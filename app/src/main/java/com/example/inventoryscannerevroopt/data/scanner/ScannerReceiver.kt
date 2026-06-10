package com.example.inventoryscannerevroopt.data.scanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

class ScannerReceiver(
    private val onBarcodeScanned: (BarcodeData) -> Unit
): BroadcastReceiver() {

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
       val barcode = intent?.getStringExtra(
           "SCAN_BARCODE1"
       )
        val state = intent?.getStringExtra(
            "SCAN_STATE"
        )

        val barcodeType = intent?.getIntExtra(
            "SCAN_BARCODE_TYPE",
            -1
            )?: -1
        if(!barcode.isNullOrEmpty()){
            onBarcodeScanned(
                BarcodeData(
                    barcode = barcode,
                    barcodeType = barcodeType,
                    isSuccess = state == "ok"
                )
            )
        }
    }


}