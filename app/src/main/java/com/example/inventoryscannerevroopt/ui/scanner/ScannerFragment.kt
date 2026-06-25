package com.example.inventoryscannerevroopt.ui.scanner

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.inventoryscannerevroopt.data.scanner.ScannerManager
import com.example.inventoryscannerevroopt.data.scanner.ScannerMode
import com.example.inventoryscannerevroopt.data.scanner.ScannerModeManager
import com.example.inventoryscannerevroopt.data.scanner.provider.DeviceInfoProvider
import com.example.inventoryscannerevroopt.databinding.FragmentScannerBinding
import com.example.inventoryscannerevroopt.domain.model.DeviceInfo
import kotlinx.coroutines.launch

class ScannerFragment: Fragment() {
    private var _binding: FragmentScannerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScannerViewModel by viewModels{
        ScannerViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScannerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val deviceInfo = viewModel.deviceInfo
        binding.tvBrand.text = "Бренд: ${deviceInfo.brandName}"
        binding.tvModel.text = "Модель: ${deviceInfo.modelName}"
        binding.tvVersion.text = "Версия: ${deviceInfo.androidVersion}"

        observeScannerMode()

        binding.btnSetBroadcastScanner.setOnClickListener {
            viewModel.changeMode(ScannerMode.BROADCAST)
            showDialog("Выбран быстрый сканнер")
        }
        binding.btnSetKeystrokeScanner.setOnClickListener {
            viewModel.changeMode(ScannerMode.KEYSTROKE)
            showDialog("Выбран клавиатурный сканер")
        }
    }

    private fun observeScannerMode(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.scannerMode.collect{ mode->
                    binding.tvCurrentMode.text = "Текущий режим: ${mode.title}"
                }
            }
        }

    }

    private fun showDialog(message: String){
        AlertDialog.Builder(requireContext())
            .setTitle("Настройка")
            .setMessage(message)
            .setPositiveButton("Ок", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}