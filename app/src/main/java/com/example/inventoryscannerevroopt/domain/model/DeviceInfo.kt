package com.example.inventoryscannerevroopt.domain.model
import android.os.Build

fun checkDeviceInfo(){
    val brandName = Build.BRAND
    val modelName = Build.MODEL
    val androidVersion = Build.VERSION.RELEASE
    println(brandName)
    println(modelName)
    println(androidVersion)
}
