package com.example.inventoryscannerevroopt.data.scanner.provider

interface ScannerProvider{

    //val supportedModes: Set<ScannerMode>

    fun startScanning()

    fun stopScanning()

    fun registerReceiver()

    fun unregisterReceiver()

}