package com.example.inventoryscannerevroopt.ui.scanner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryscannerevroopt.data.repository.ScannerRepository
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.domain.model.BarcodeData
import com.example.inventoryscannerevroopt.domain.model.DeviceInfo
import com.example.inventoryscannerevroopt.domain.model.ScannerUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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

                _uiState.value = _uiState.value.copy(currentMode = mode)

            }
        }

        viewModelScope.launch {
            repository.observeBarcodes().collect { barcode ->
                _uiState.update { currentState->
                    when(currentState.currentMode){
                        ScannerMode.BROADCAST -> {
                            Log.d("Scanner", "Broadcast barcode: $barcode")
                            currentState.copy(broadcastBarcode = barcode)
                        }

                        ScannerMode.KEYSTROKE -> {
                            Log.d("Scanner", "Keystroke barcode: $barcode")
                            currentState.copy(keystrokeBarcode = barcode)
                        }

                        ScannerMode.EDIT_TEXT -> {
                            Log.d("Scanner", "EDIT_TEXT: $barcode")
                            currentState.copy(editTextBarcode = barcode)
                        }
                    }
                }
            }
        }



    }

    fun registerScanner() {
        repository.registerScanner()
    }

    fun unregisterScanner() {
        repository.unregisterScanner()
    }

    fun changeMode(mode: ScannerMode) {

        viewModelScope.launch {

            repository.changeMode(mode)

        }

    }
}