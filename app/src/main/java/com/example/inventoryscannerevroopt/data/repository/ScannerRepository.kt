package com.example.inventoryscannerevroopt.data.repository

import com.example.inventoryscannerevroopt.data.datastore.ScannerPreferences
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.data.scanner.ScannerModeManager
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider
import com.example.inventoryscannerevroopt.domain.model.DeviceInfo
import kotlinx.coroutines.flow.Flow

class ScannerRepository(
    private val preferences: ScannerPreferences,
    private val scannerModeManager: ScannerModeManager,
    private val deviceInfoProvider: DeviceInfoProvider
) {

    suspend fun changeMode(mode: ScannerMode) {
        scannerModeManager.setMode(mode)
        preferences.saveScannerMode(mode)
    }

    fun observeMode(): Flow<ScannerMode> {
        return preferences.getScannerMode()
    }

    fun getDeviceInfo(): DeviceInfo {
        return deviceInfoProvider.getDeviceInfo()
    }
}