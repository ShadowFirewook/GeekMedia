package com.example.geekmedia.presentation.ui.geekmedia_info_fragment

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.geekmedia.databinding.FragmentGeekMediaInformationBinding
import com.example.geekmedia.presentation.ui.base.BaseFragment

class GeekMediaInformationFragment : BaseFragment<FragmentGeekMediaInformationBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentGeekMediaInformationBinding {
        return FragmentGeekMediaInformationBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}