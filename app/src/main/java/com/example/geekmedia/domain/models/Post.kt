package com.example.geekmedia.domain.models

import com.example.geekmedia.core.DEFAULT_ID_FOR_POST

class Post(
    val id: Int = DEFAULT_ID_FOR_POST,
    val articles: List<Article>,
    val category: Category,
    val created_date: String,
    val created_date_time: String,
    val description: String,
    val image: String,
    val title: String
){
    data class Article(
        val description: String,
        val image: String,
        val title: String
    )

    data class Category(
        val en_title: String,
        val ru_title: String
    )
}