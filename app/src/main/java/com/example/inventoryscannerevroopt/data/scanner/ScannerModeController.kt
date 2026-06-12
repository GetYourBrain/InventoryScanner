package com.example.inventoryscannerevroopt.data.scanner

interface ScannerModeController {

    val supportedModes: Set<ScannerMode>

    fun setMode(mode: ScannerMode)

    fun getCurrentMode(): ScannerMode?
}