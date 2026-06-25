package com.example.inventoryscannerevroopt.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.inventoryscannerevroopt.R
import com.example.inventoryscannerevroopt.databinding.ActivityMainBinding
import com.example.inventoryscannerevroopt.databinding.FragmentSettingsBinding
import com.example.inventoryscannerevroopt.ui.scanner.ScannerFragment
import kotlinx.coroutines.launch

class SettingsFragment: Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels{
        SettingsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeConnectionSettings()

        binding.btnSave.setOnClickListener {
            saveSettings()
        }

        binding.btnScanner.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ScannerFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun observeConnectionSettings(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.connectionSettings.collect { settings ->
                    binding.etIp.setText(settings.serverIp)

                    if (settings.serverPort != 0){
                        binding.etPort.setText(settings.serverPort.toString())
                    }
                    if (settings.warehouse != 0){
                        binding.etWarehouse.setText(settings.warehouse.toString())
                    }
                }
            }
        }
    }
    private fun saveSettings(){
        val ip = binding.etIp.text.toString()
        val port = binding.etPort.text.toString().toIntOrNull()?: 0
        val warehouse = binding.etWarehouse.text.toString().toIntOrNull()?: 0

        viewModel.saveConnectionSettings(
            ip,
            port,
            warehouse
        )

        Toast.makeText(
            requireContext(),
            "Настройки сохранены",
            Toast.LENGTH_SHORT
        ).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}