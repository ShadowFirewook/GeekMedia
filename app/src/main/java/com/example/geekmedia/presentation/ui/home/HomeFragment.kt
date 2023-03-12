package com.example.geekmedia.presentation.ui.home

import android.view.LayoutInflater
import com.example.geekmedia.databinding.FragmentHomeBinding
import com.example.geekmedia.presentation.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

}