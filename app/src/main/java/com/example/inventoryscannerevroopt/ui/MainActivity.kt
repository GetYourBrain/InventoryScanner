package com.example.inventoryscannerevroopt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryscannerevroopt.R
import com.example.inventoryscannerevroopt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val deviceInfo = viewModel.getDeviceInfo()
        binding.tvBrand.text = deviceInfo.brandName
        binding.tvModel.text = deviceInfo.modelName
        binding.tvAndroid.text = deviceInfo.androidVersion

    }
}