package com.example.geekmedia.presentation.ui.search_menu

import android.view.LayoutInflater
import com.example.geekmedia.databinding.FragmentSearchMenuBinding
import com.example.geekmedia.presentation.base.BaseFragment

class SearchMenuFragment : BaseFragment<FragmentSearchMenuBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSearchMenuBinding {
        return FragmentSearchMenuBinding.inflate(layoutInflater)
    }

}