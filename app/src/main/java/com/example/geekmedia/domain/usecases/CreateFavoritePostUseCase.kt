package com.example.geekmedia.domain.usecases

import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.repositories.FavoritesNewsRepository
import javax.inject.Inject

class CreateFavoritePostUseCase @Inject constructor(
    private val favoritesNewsRepository: FavoritesNewsRepository
) {

    fun createFavoritePost(favoritePost: News.Item) = favoritesNewsRepository.createFavoritePost(favoritePost)

}