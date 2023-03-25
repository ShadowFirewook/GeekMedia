package com.example.geekmedia.presentation.ui.profile

import android.net.Uri
import com.example.geekmedia.domain.models.Post
import com.example.geekmedia.domain.usecases.CreateFavoritePostUseCase
import com.example.geekmedia.domain.usecases.GetPostUseCase
import com.example.geekmedia.domain.usecases.GetUserAvatarUseCase
import com.example.geekmedia.domain.usecases.SaveUserAvatarUseCase
import com.example.geekmedia.presentation.ui.UIState
import com.example.geekmedia.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
//    private val saveUserAvatarUseCase: SaveUserAvatarUseCase,
//    private val getUserAvatarUseCase: GetUserAvatarUseCase
) : BaseViewModel() {

//    private val _saveUserAvatarState = MutableStateFlow<UIState<Uri>>(UIState.Empty())
//    val saveUserAvatarState = _saveUserAvatarState
//
//    suspend fun saveUserAvatar(imageUri: Uri){
//        saveUserAvatarUseCase.saveUserAvatar(imageUri).collectFlow(_saveUserAvatarState)
//    }
//
//    private val _getUserAvatarState = MutableStateFlow<UIState<String>>(UIState.Empty())
//    val getUserAvatarState = _getUserAvatarState
//
//    suspend fun getUserAvatar(){
//        getUserAvatarUseCase.getUserAvatar().collectFlow(_getUserAvatarState)
//    }

}