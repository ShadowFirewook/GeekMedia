package com.example.geekmedia.presentation.ui.post

import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.models.Post
import com.example.geekmedia.domain.usecases.CreateFavoritePostUseCase
import com.example.geekmedia.domain.usecases.GetPostUseCase
import com.example.geekmedia.presentation.ui.UIState
import com.example.geekmedia.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PostViewModel@Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val createFavoritePostUseCase: CreateFavoritePostUseCase
) : BaseViewModel() {

    private val _getPostState = MutableStateFlow<UIState<Post>>(UIState.Empty())
    val getPostState = _getPostState//todo .asStateFlow()

    fun getPost(id:Int){
        getPostUseCase.getPost(id).collectFlow(_getPostState)
    }

    private val _createFavoritePostState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createFavoritePostState = _createFavoritePostState//todo.asStateFlow()

    fun createFavoritePost(favoritePost: News.Item){
        createFavoritePostUseCase.createFavoritePost(favoritePost).collectFlow(_createFavoritePostState)
    }

}