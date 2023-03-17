package com.example.geekmedia.presentation.ui.profile

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.geekmedia.R
import com.example.geekmedia.databinding.FragmentProfileBinding
import com.example.geekmedia.presentation.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun initView() {
        aboutGeekmediaInfoClick()
        clearCache()
        binding.tvDisablingSavesTraffic.setOnClickListener {

        }
    }

    private fun aboutGeekmediaInfoClick() {
        binding.tvAboutGeekmedia.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_geekMediaInformationFragment)
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

}