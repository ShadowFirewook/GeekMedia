package com.example.geekmedia.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>: Fragment() {

    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateViewBinding(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getData()
        initAdapter()
        initViewModel()
        initView()
        initListener()
        initClick()
    }

    protected open fun getData() {}
    protected open fun initAdapter() {}
    protected open fun initViewModel() {}
    protected open fun initView() {}
    protected open fun initListener() {}
    protected open fun initClick() {}

}