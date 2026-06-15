package com.example.inventoryscannerevroopt.data.scanner.provider

import android.util.Log
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode

interface ScannerProvider{
    fun startScanning()

    fun stopScanning()

    fun registerReceiver()

    fun unregisterReceiver()

}