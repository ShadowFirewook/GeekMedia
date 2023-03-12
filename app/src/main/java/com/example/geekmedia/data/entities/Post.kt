package com.example.geekmedia.data.entities

data class Post(
    val articles: List<Article>,
    val category: Category,
    val created_date: String,
    val created_date_time: String,
    val description: String,
    val id: Int,
    val image: String,
    val title: String
) {
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