package com.example.geekmedia.data.local

import androidx.room.*
import com.example.geekmedia.data.entities.NewsEntity

@Dao
interface FavoritesNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createFavoritePost(favoritePost: NewsEntity.ItemEntity)

    @Delete
    suspend fun deleteFavoritePost(favoritePost: NewsEntity.ItemEntity)

    @Query("SELECT * FROM favorites_news")
    suspend fun getFavoritesNews(): List<NewsEntity.ItemEntity>

}