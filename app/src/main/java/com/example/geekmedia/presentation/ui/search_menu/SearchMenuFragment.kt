package com.example.geekmedia.presentation.ui.search_menu

import android.view.LayoutInflater
import com.example.geekmedia.databinding.FragmentSearchBinding

import com.example.geekmedia.presentation.ui.base.BaseFragment

class SearchMenuFragment : BaseFragment<FragmentSearchBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

}