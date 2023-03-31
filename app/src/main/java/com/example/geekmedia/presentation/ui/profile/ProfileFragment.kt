package com.example.geekmedia.presentation.ui.profile

import android.app.AlertDialog
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.geekmedia.R
import com.example.geekmedia.databinding.FragmentProfileBinding
import com.example.geekmedia.presentation.ui.base.BaseFragment
import com.example.geekmedia.presentation.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private var imageUri : Uri? = null

//    private val viewModel by viewModels<ProfileViewModel>()
    private val mSelectedPicDataResult = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { result ->
        if (result != null) {
            imageUri = result
            binding.ivUser.setImageURI(imageUri)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.ivUser.setOnClickListener{
            mSelectedPicDataResult.launch("image/*")
        }
    }

}