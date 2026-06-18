package com.example.inventoryscannerevroopt.data.scanner.provider.brand.newland

import android.content.Intent
import com.example.inventoryscannerevroopt.data.scanner.provider.receiver.ScannerReceiver
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

class NewlandScannerReceiver(
    onBarcodeScanned: (BarcodeData) -> Unit
): ScannerReceiver(onBarcodeScanned) {

    override fun extractBarcodeData(intent: Intent?): BarcodeData? {

        val barcode = intent?.getStringExtra(
            "SCAN_BARCODE1"
        )

        val barcodeType = intent?.getIntExtra(
            "SCAN_BARCODE_TYPE",
            -1
        ) ?: -1
        return if (!barcode.isNullOrEmpty()) {
                BarcodeData(
                    barcode = barcode.replace("\u001D", "[GS]"),
                    barcodeType = barcodeType,
                )

        }else null
    }
}