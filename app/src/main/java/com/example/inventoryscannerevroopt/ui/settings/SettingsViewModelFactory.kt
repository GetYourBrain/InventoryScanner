package com.example.inventoryscannerevroopt.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryscannerevroopt.data.datastore.ScannerPreferences
import com.example.inventoryscannerevroopt.data.repository.SettingsRepository

class SettingsViewModelFactory(
    context: Context
): ViewModelProvider.Factory {
    private val repository = SettingsRepository(
        ScannerPreferences(context.applicationContext)
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)){
            return SettingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Неизвестный класс ViewModel")
    }
}