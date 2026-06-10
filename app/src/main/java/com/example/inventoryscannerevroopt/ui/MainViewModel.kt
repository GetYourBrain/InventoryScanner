package com.example.inventoryscannerevroopt.ui

import androidx.lifecycle.ViewModel
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider
import com.example.inventoryscannerevroopt.domain.model.DeviceInfo

class MainViewModel : ViewModel() {
    private val deviceInfoProvider = DeviceInfoProvider()

    fun getDeviceInfo(): DeviceInfo {
        return deviceInfoProvider.getDeviceInfo()
    }
}