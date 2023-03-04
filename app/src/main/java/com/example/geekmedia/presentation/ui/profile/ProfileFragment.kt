package com.example.geekmedia.presentation.ui.profile

import android.view.LayoutInflater
import com.example.geekmedia.databinding.FragmentProfileBinding
import com.example.geekmedia.presentation.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

}