package com.example.inventoryscannerevroopt.ui.scanner

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryscannerevroopt.data.datastore.ScannerPreferences
import com.example.inventoryscannerevroopt.data.repository.ScannerRepository
import com.example.inventoryscannerevroopt.data.scanner.ScannerManager
import com.example.inventoryscannerevroopt.data.scanner.ScannerModeManager
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider

class ScannerViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {

    private val repository = ScannerRepository(
        preferences = ScannerPreferences(context.applicationContext),
        scannerModeManager = ScannerModeManager(context.applicationContext),
        deviceInfoProvider = DeviceInfoProvider(),
        scannerManager = ScannerManager(context.applicationContext)
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ScannerViewModel::class.java)) {

            return ScannerViewModel(repository) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}