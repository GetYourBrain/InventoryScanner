package com.example.inventoryscannerevroopt.data.scanner.provider.brand

import android.util.Log
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProvider

class UnsupportedScannerProvider: ScannerProvider {
    override fun startScanning() {
        Log.d("SCAN_TEST", "Provider starScanning")
    }

    override fun stopScanning() {
        Log.d("SCAN_TEST", "Provider stopScanning")
    }

    override fun registerReceiver() {
        Log.d("SCAN_TEST", "Provider registerReceiver")
    }

    override fun unregisterReceiver() {
        Log.d("SCAN_TEST", "Provider unregisterReceiver")
    }
}