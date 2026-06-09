package com.example.inventoryscannerevroopt.data.scanner

import android.os.Build
import com.example.inventoryscannerevroopt.domain.model.DeviceInfo

class DeviceInfoProvider {
    val brandName = Build.BRAND
    val modelName = Build.MODEL
    val androidVersion = Build.VERSION.RELEASE

    fun getDeviceInfo(): DeviceInfo{

        return DeviceInfo(
            brandName,
            modelName,
            androidVersion)
    }
}