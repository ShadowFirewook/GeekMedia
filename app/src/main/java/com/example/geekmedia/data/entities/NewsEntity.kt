package com.example.geekmedia.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsEntity(
    val count: Int,
//    val next: Any,
//    val previous: Any,
    val results: List<ItemEntity>
) {

    @Entity(tableName = "favorites_news")
    data class ItemEntity(
        val category: String,
        val created_date: String,
        val created_date_time: String,
        val description: String,
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val image: String,
        val title: String
    ) {
//        constructor() : this("","","","",121212121,"","")
    }
}