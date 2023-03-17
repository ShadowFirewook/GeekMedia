package com.example.geekmedia.presentation.ui.home

import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.usecases.*
import com.example.geekmedia.presentation.ui.UIState
import com.example.geekmedia.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val getFavoritesNewsUseCase: GetFavoritesNewsUseCase,
    private val deleteFavoritePostUseCase: DeleteFavoritePostUseCase,
    private val createFavoritePostUseCase: CreateFavoritePostUseCase

) : BaseViewModel() {


    private val _getNewsState = MutableStateFlow<UIState<News>>(UIState.Empty())
    val getNewsState = _getNewsState//todo.asStateFlow()

    fun getNews(){
        getNewsUseCase.getNews( ).collectFlow(_getNewsState)
    }

    private val _createFavoritePostState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createFavoritePostState = _createFavoritePostState//todo.asStateFlow()

    fun createFavoritePost(favoritePost: News.Item){
        createFavoritePostUseCase.createFavoritePost(favoritePost).collectFlow(_createFavoritePostState)
    }

    private val _getFavoritesNewsState = MutableStateFlow<UIState<List<News.Item>>>(UIState.Empty())
    val getFavoritesNewsState = _getFavoritesNewsState

    fun getFavoritesNews(){
        getFavoritesNewsUseCase.getFavoritesNews().collectFlow(_getFavoritesNewsState)
    }

    private val _deleteFavoritePostState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteFavoritePostState = _deleteFavoritePostState

    fun deleteFavoritePost(favoritePost: News.Item){
        deleteFavoritePostUseCase.deleteFavoritePost(favoritePost).collectFlow(_deleteFavoritePostState)
    }

}