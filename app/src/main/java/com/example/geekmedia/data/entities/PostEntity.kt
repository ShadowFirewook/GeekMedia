package com.example.geekmedia.data.entities


data class PostEntity(
    val articles: List<ArticleEntity>,
    val category: CategoryEntity,
    val created_date: String,
    val created_date_time: String,
    val description: String,
    val id: Int,
    val image: String,
    val title: String
) {
    data class ArticleEntity(
        val description: String,
        val image: String?,
        val title: String
    )

    data class CategoryEntity(
        val en_title: String,
        val ru_title: String
    )
}