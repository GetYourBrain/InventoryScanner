package com.example.inventoryscannerevroopt.data.scanner

import com.example.inventoryscannerevroopt.domain.model.DeviceInfo

class ScannerTypeResolver {
    fun resolve(deviceInfo: DeviceInfo): ScannerType{
        return when{
            deviceInfo.brandName!!.contains("Newland", true) ->
                ScannerType.NEWLAND

            else ->
                ScannerType.UNKNOWN
        }
    }
}