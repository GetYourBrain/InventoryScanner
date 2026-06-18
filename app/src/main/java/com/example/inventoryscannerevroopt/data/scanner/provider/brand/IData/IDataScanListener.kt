package com.example.inventoryscannerevroopt.data.scanner.provider.brand.IData

import android.os.IScanListener
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

class IDataScanListener(
    private val onBarcodeScanned: (BarcodeData) -> Unit
): IScanListener {
    override fun onScanResults(
        data: String?,
        type: Int,
        decodeTime: Long,
        keyDownTime: Long,
        imagePath: String?){

        if (data.isNullOrBlank()) return

        onBarcodeScanned(
            BarcodeData(
                barcode = data.replace("\u001D", "[GS]"),
                barcodeType = type
            )
        )
    }
}