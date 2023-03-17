package com.example.geekmedia.domain.repositories

import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.News
import kotlinx.coroutines.flow.Flow

interface FavoritesNewsRepository {

    fun createFavoritePost(favoritePost: News.Item): Flow<Status<Unit>>

    fun deleteFavoritePost(favoritePost: News.Item): Flow<Status<Unit>>

    fun getFavoritesNews(): Flow<Status<List<News.Item>>>
}