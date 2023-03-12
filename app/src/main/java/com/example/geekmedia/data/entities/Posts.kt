package com.example.geekmedia.data.entities

data class Posts(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Item>
) {
    data class Item(
        val category: String,
        val created_date: String,
        val created_date_time: String,
        val description: String,
        val id: Int,
        val image: String,
        val title: String
    )
}