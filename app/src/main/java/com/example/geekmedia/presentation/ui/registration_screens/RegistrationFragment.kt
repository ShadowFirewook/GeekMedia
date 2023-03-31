package com.example.geekmedia.presentation.ui.registration_screens

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.geekmedia.databinding.FragmentRegistrationBinding
import com.example.geekmedia.domain.models.UserData
import com.example.geekmedia.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    private val viewModel by viewModels<RegistrationViewModel>()
    private val userData = UserData(first_name = listOf(binding.registration.tvName.text.toString()),
    listOf(binding.registration.tvSurname.text.toString()),
        listOf(binding.registration.tvPassword.text.toString()),
        listOf(binding.registration.phoneNumber.text.toString())
    )

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(layoutInflater)
    }

    override fun initRequests() {
        binding.registration.btnRegister.setOnClickListener {
            with(binding){
                if (registration.tvName.text.toString().isNotEmpty() &&
                    registration.tvSurname.text.toString().isNotEmpty() &&
                    registration.phoneNumber.text.toString().isNotEmpty() &&
                    registration.tvPassword.text.toString().isNotEmpty() &&
                    registration.tvConfirmPassword.text.toString().isNotEmpty()
                ) {
//                    viewModel.registerUser(
//                        userData = userData
//                    )
                    } else {
                        //todo toast
                }

            }
        }
    }

    override fun notifySubscribers() {

    }

}