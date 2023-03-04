package com.example.geekmedia.presentation.ui.favorites

import android.view.LayoutInflater
import com.example.geekmedia.databinding.FragmentFavoritesBinding
import com.example.geekmedia.presentation.ui.base.BaseFragment

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(layoutInflater)
    }

}