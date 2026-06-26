package com.example.inventoryscannerevroopt.domain.model

import com.example.inventoryscannerevroopt.data.scanner.ScannerMode

data class ScannerUiState(
    val currentMode: ScannerMode = ScannerMode.KEYSTROKE,
    val broadcastBarcode: BarcodeData? = null,
    val keystrokeBarcode: BarcodeData? = null,
    val editTextBarcode: BarcodeData? = null
)