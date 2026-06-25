package com.example.inventoryscannerevroopt.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.domain.model.ConnectionSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("scanner_settings")

class ScannerPreferences(private val context: Context) {
    companion object {
        private val SCANNER_MODE =
            stringPreferencesKey("scanner_mode")
        private val SERVER_IP =
            stringPreferencesKey("server_ip")

        private val SERVER_PORT =
            intPreferencesKey("server_port")

        private val WAREHOUSE =
            intPreferencesKey("warehouse")
    }

    suspend fun saveScannerMode(mode: ScannerMode) {
        context.dataStore.edit { preferences ->
            preferences[SCANNER_MODE] = mode.name

        }
    }

    fun getScannerMode(): Flow<ScannerMode> {
        return context.dataStore.data.map { preferences ->
            val mode = preferences[SCANNER_MODE]

            mode?.let {
                ScannerMode.valueOf(it)
            } ?: ScannerMode.KEYSTROKE
        }
    }

    suspend fun saveConnectionSettings(
        settings: ConnectionSettings
    ) {
        context.dataStore.edit { preferences ->

            preferences[SERVER_IP] = settings.serverIp
            preferences[SERVER_PORT] = settings.serverPort
            preferences[WAREHOUSE] = settings.warehouse
        }
    }

    fun getConnectionSettings(): Flow<ConnectionSettings> {

        return context.dataStore.data.map { preferences ->

            ConnectionSettings(

                serverIp = preferences[SERVER_IP] ?: "",

                serverPort = preferences[SERVER_PORT] ?: 0,

                warehouse = preferences[WAREHOUSE] ?: 0

            )
        }
    }

    suspend fun getConnectionSettingsOnce(): ConnectionSettings {

        return getConnectionSettings().first()

    }
}
