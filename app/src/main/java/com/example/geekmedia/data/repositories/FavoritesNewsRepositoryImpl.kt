package com.example.geekmedia.data.repositories

import com.example.geekmedia.data.local.FavoritesNewsDao
import com.example.geekmedia.data.mappers.toItem
import com.example.geekmedia.data.mappers.toItemEntity
import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.repositories.FavoritesNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesNewsRepositoryImpl @Inject constructor(
    private val favoritesNewsDao: FavoritesNewsDao
): BaseRepository(), FavoritesNewsRepository {

    override fun createFavoritePost(favoritePost: News.Item): Flow<Status<Unit>> = doRequest {
        favoritesNewsDao.createFavoritePost(favoritePost.toItemEntity())
    }

    override fun deleteFavoritePost(favoritePost: News.Item): Flow<Status<Unit>> = doRequest{
        favoritesNewsDao.deleteFavoritePost(favoritePost.toItemEntity())
    }

    override fun getFavoritesNews(): Flow<Status<List<News.Item>>> = doRequest {
        favoritesNewsDao.getFavoritesNews().map { it.toItem() }.sortedByDescending { it.created_date_time }
    }
}