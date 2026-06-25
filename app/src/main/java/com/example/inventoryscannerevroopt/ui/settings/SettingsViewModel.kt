package com.example.inventoryscannerevroopt.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventoryscannerevroopt.data.repository.SettingsRepository
import com.example.inventoryscannerevroopt.domain.model.ConnectionSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository
): ViewModel() {
    val connectionSettings: StateFlow<ConnectionSettings> =
            repository.observeConnectionSettings()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = ConnectionSettings()
                )
    fun saveConnectionSettings(
        serverIp: String,
        serverPort: Int,
        warehouse: Int){
        viewModelScope.launch {
            repository.saveConnectionSettings(
                ConnectionSettings(
                    serverIp = serverIp,
                    serverPort = serverPort,
                    warehouse = warehouse
                )
            )
        }
    }
}