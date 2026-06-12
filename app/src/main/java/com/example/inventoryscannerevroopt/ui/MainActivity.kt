package com.example.inventoryscannerevroopt.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.inventoryscannerevroopt.ui.ScannerFragment
import com.example.inventoryscannerevroopt.ui.ScannerViewModel
import com.example.inventoryscannerevroopt.ui.ScannerViewModelFactory
import com.example.inventoryscannerevroopt.ui.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.inventoryscannerevroopt.R

class MainActivity : AppCompatActivity() {

    val scannerViewModel: ScannerViewModel by viewModels {
        ScannerViewModelFactory(application)
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        scannerViewModel.registerReceiver()

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    ScannerFragment()
                )
                .commit()
        }

        findViewById<BottomNavigationView>(
            R.id.bottomNavigation
        ).setOnItemSelectedListener {

            when (it.itemId) {

                R.id.menu_scanner -> {

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            ScannerFragment()
                        )
                        .commit()

                    true
                }

                R.id.menu_settings -> {

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            SettingsFragment()
                        )
                        .commit()

                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        scannerViewModel.unregisterReceiver()
    }
}