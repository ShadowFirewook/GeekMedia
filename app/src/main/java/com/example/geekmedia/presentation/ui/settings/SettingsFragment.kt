package com.example.geekmedia.presentation.ui.settings

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.geekmedia.R
import com.example.geekmedia.databinding.FragmentSettingsBinding
import com.example.geekmedia.presentation.ui.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun initView() {
        offerNewsClick()
        clearCache()
        onBackButtonPressed()
        binding.tvDisablingSavesTraffic.setOnClickListener {

        }
    }

    private fun offerNewsClick() {
        binding.cvOfferNews.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_geekMediaInformationFragment)
        }
    }

    private fun clearCache(){
        binding.tvClearCache.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Очистить кэш?")
            alertDialog.setPositiveButton("Да") { dialog, _ ->
                requireContext().cacheDir.deleteRecursively()
            }
            alertDialog.setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.create().show()
        }
    }

    private fun onBackButtonPressed(){
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}