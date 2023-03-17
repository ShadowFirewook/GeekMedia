package com.example.geekmedia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geekmedia.data.entities.NewsEntity

@Database(entities = [NewsEntity.ItemEntity::class], version = 1)
abstract class FavoritesNewsDatabase: RoomDatabase(){

    abstract fun favoritesNewsDao(): FavoritesNewsDao

}