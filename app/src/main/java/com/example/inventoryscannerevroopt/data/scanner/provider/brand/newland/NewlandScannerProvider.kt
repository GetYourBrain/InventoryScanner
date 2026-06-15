package com.example.inventoryscannerevroopt.data.scanner.provider.brand.newland

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.data.scanner.ScannerModeController
import com.example.inventoryscannerevroopt.data.scanner.provider.receiver.ScannerReceiver
import com.example.inventoryscannerevroopt.data.scanner.provider.ScannerProvider
import com.example.inventoryscannerevroopt.domain.model.BarcodeData
import com.example.inventoryscannerevroopt.data.scanner.provider.brand.newland.NewlandConstants

class NewlandScannerProvider(
    private val context: Context,
    private val onBarcodeScanned: (BarcodeData) -> Unit
): ScannerProvider, ScannerModeController {

    private val scannerReceiver = NewlandScannerReceiver(onBarcodeScanned)
    private var currentMode: ScannerMode? = null

    override val supportedModes = setOf(
        ScannerMode.BROADCAST,
        ScannerMode.KEYSTROKE,
        ScannerMode.EDIT_TEXT
    )

    override fun setMode(mode: ScannerMode) {

        currentMode = mode

        when (mode) {
            ScannerMode.BROADCAST -> {
                setBroadcastMode()
            }

            ScannerMode.KEYSTROKE -> {
                setKeystrokeMode()
            }

            ScannerMode.EDIT_TEXT -> {
                setEditTextMode()
            }
        }

    }

    override fun getCurrentMode(): ScannerMode? {
        return currentMode
    }

    private fun setBroadcastMode(){
        sendConfigIntent(
            scanMode = 3,
            prefixEnabled = false,
            suffixEnabled = false
        )
    }

    private fun setKeystrokeMode(){
        sendConfigIntent(
            scanMode = 2,
            prefixEnabled = true,
            suffixEnabled = true
        )
    }

    private fun setEditTextMode(){
        sendConfigIntent(
            scanMode = 1,
            prefixEnabled = false,
            suffixEnabled = false
        )
    }

    private fun sendConfigIntent(
        scanMode: Int,
        prefixEnabled: Boolean,
        suffixEnabled: Boolean
    ){
        val intent = Intent(NewlandConstants.ACTION_BAR_SCANCFG).apply {

            putExtra(NewlandConstants.EXTRA_SCAN_POWER, 1)

            putExtra(NewlandConstants.EXTRA_TRIG_MODE, 2)

            putExtra(NewlandConstants.SCAN_TIMEOUT, 3000L)
            putExtra(NewlandConstants.SCAN_INTERVAL, 10L)

            putExtra(NewlandConstants.EXTRA_SCAN_MODE, scanMode)

            putExtra(NewlandConstants.EXTRA_SCAN_AUTOENT, 0)

            putExtra(NewlandConstants.EXTRA_SCAN_NOTY_SND, 0)
            putExtra(NewlandConstants.EXTRA_SCAN_NOTY_VIB, 0)
            putExtra(NewlandConstants.EXTRA_SCAN_NOTY_LED, 1)

            putExtra(NewlandConstants.SCAN_PREFIX_ENABLE,1)
            putExtra(NewlandConstants.SCAN_PREFIX, "3F")

            putExtra(NewlandConstants.SCAN_SUFFIX_ENABLE, 1)
            putExtra(NewlandConstants.SCAN_SUFFIX, "3D")

            putExtra(NewlandConstants.SCAN_ENCODE, 4)
        }

        context.sendBroadcast(intent)
        Log.d(
            "SCAN_TEST",
            "Switch scanner mode: $scanMode"
        )
    }
    override fun startScanning() {
        Log.d("SCAN_TEST", "Provider startScanning")
       val intent = Intent("nlscan.action.SCANNER_TRIG")
        context.sendBroadcast(intent)
    }

    override fun stopScanning() {
        val intent = Intent("nlscan.action.STOP_SCAN")
        context.sendBroadcast(intent)
    }

    override fun registerReceiver() {
        Log.d("SCAN_TEST", "Provider registerReceiver")
        registerScannerReceiver()
    }

    override fun unregisterReceiver() {
        context.unregisterReceiver(
            scannerReceiver
        )
    }

    private fun registerScannerReceiver(){
        val filter = IntentFilter("nlscan.action.SCANNER_RESULT")
        ContextCompat.registerReceiver(
            context,
            scannerReceiver,
            filter,
            ContextCompat.RECEIVER_EXPORTED
        )
    }

}