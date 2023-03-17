package com.example.geekmedia.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geekmedia.domain.Status
import com.example.geekmedia.presentation.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<Status<T>>.collectFlow(_state: MutableStateFlow<UIState<T>>) {
        viewModelScope.launch(Dispatchers.IO){
            this@collectFlow.collect{
                when(it){
                    is Status.Error -> {
                        _state.value = UIState.Error(it.error!!)
                    }
                    is Status.Loading -> {
                        _state.value = UIState.Loading()
                    }
                    is Status.Success -> {
                        if (it.data != null)
                            _state.value = UIState.Success(it.data)
                    }
                }
            }
        }

    }

}