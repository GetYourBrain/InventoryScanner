package com.example.inventoryscannerevroopt.ui.scanner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryscannerevroopt.data.repository.ScannerRepository
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.domain.model.BarcodeData
import com.example.inventoryscannerevroopt.domain.model.DeviceInfo
import com.example.inventoryscannerevroopt.domain.model.ScannerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ScannerViewModel(
    private val repository: ScannerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScannerUiState())

    val uiState: StateFlow<ScannerUiState> =
        _uiState.asStateFlow()

    val deviceInfo: DeviceInfo = repository.getDeviceInfo()

    init {

        viewModelScope.launch {

            repository.observeMode().collect { mode ->

                _uiState.value =
                    _uiState.value.copy(
                        currentMode = mode
                    )

            }

        }

    }

//    val scannerMode: StateFlow<ScannerMode> =
//        repository.observeMode()
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5000),
//                initialValue = ScannerMode.KEYSTROKE
//            )

    fun changeMode(mode: ScannerMode) {

        viewModelScope.launch {

            repository.changeMode(mode)

        }

    }

    fun onBarcodeScanned(barcode: BarcodeData) {

        _uiState.value =

            when (_uiState.value.currentMode) {
                ScannerMode.BROADCAST -> {
                    Log.d("Scanner", "Broadcast mode, barcode: $barcode")
                    _uiState.value.copy(broadcastBarcode = barcode)
                }
                ScannerMode.KEYSTROKE -> {
                    Log.d("Scanner", "Keystroke mode, barcode: $barcode")
                    _uiState.value.copy(keystrokeBarcode = barcode)
                }
                ScannerMode.EDIT_TEXT -> {
                    Log.d("Scanner", "EditText mode, barcode: $barcode")
                    _uiState.value.copy(editTextBarcode = barcode)
                }
            }

    }
}