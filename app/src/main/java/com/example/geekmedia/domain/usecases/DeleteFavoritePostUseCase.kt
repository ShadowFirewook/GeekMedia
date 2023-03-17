package com.example.geekmedia.domain.usecases

import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.repositories.FavoritesNewsRepository
import javax.inject.Inject

class DeleteFavoritePostUseCase @Inject constructor(
    private val favoritesNewsRepository: FavoritesNewsRepository
) {

    fun deleteFavoritePost(favoritePost: News.Item) = favoritesNewsRepository.deleteFavoritePost(favoritePost)
}