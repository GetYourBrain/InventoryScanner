package com.example.inventoryscannerevroopt.data.scanner.provider

import android.content.Context
import com.example.inventoryscannerevroopt.data.scanner.ScannerType
import com.example.inventoryscannerevroopt.data.scanner.provider.brand.NewlandScannerProvider
import com.example.inventoryscannerevroopt.data.scanner.provider.brand.UnsupportedScannerProvider
import com.example.inventoryscannerevroopt.domain.model.BarcodeData

object ScannerProviderFactory {

    fun createScanner(
        type: ScannerType,
        context: Context,
        onBarcodeScanned: (BarcodeData) -> Unit
    ): ScannerProvider{
        return when(type){
            ScannerType.NEWLAND -> NewlandScannerProvider(
                context,
                onBarcodeScanned
            )

            ScannerType.UNKNOWN -> UnsupportedScannerProvider()
        }
    }
}