package com.example.inventoryscannerevroopt.ui.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryscannerevroopt.data.repository.ScannerRepository
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.domain.model.DeviceInfo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ScannerViewModel(
    private val repository: ScannerRepository
) : ViewModel() {

    val deviceInfo: DeviceInfo = repository.getDeviceInfo()

    val scannerMode: StateFlow<ScannerMode> =
        repository.observeMode()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ScannerMode.KEYSTROKE
            )

    fun changeMode(mode: ScannerMode) {
        viewModelScope.launch {
            repository.changeMode(mode)
        }
    }
}