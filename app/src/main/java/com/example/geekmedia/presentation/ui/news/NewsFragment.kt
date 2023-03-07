package com.example.geekmedia.presentation.ui.news

import android.view.LayoutInflater
import com.example.geekmedia.databinding.FragmentNewsBinding
import com.example.geekmedia.presentation.base.BaseFragment

class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentNewsBinding {
        return FragmentNewsBinding.inflate(layoutInflater)
    }

}