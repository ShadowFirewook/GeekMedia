package com.example.geekmedia.domain.usecases

import com.example.geekmedia.domain.repositories.FavoritesNewsRepository
import javax.inject.Inject

class GetFavoritesNewsUseCase @Inject constructor(
    private val favoritesNewsRepository: FavoritesNewsRepository
) {

  fun getFavoritesNews() = favoritesNewsRepository.getFavoritesNews()

}