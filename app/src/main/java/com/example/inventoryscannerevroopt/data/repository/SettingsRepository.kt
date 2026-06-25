package com.example.inventoryscannerevroopt.data.repository

import com.example.inventoryscannerevroopt.data.datastore.ScannerPreferences
import com.example.inventoryscannerevroopt.domain.model.ConnectionSettings
import kotlinx.coroutines.flow.Flow

class SettingsRepository(
    private val preferences: ScannerPreferences
) {
    suspend fun saveConnectionSettings(settings: ConnectionSettings){
        preferences.saveConnectionSettings(settings)
    }
    fun observeConnectionSettings(): Flow<ConnectionSettings> {
        return preferences.getConnectionSettings()
    }
    suspend fun getConnectionSettingsOnce(): ConnectionSettings{
        return preferences.getConnectionSettingsOnce()
    }
}