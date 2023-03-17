package com.example.geekmedia.presentation.ui.favorites_news

import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.usecases.DeleteFavoritePostUseCase
import com.example.geekmedia.domain.usecases.GetFavoritesNewsUseCase
import com.example.geekmedia.presentation.ui.UIState
import com.example.geekmedia.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoritesNewsViewModel @Inject constructor(
    private val getFavoritesNewsUseCase: GetFavoritesNewsUseCase,
    private val deleteFavoritePostUseCase: DeleteFavoritePostUseCase
): BaseViewModel() {

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