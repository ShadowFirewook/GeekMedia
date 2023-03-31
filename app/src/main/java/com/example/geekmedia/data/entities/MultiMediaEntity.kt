package com.example.geekmedia.data.entities

data class MultiMediaEntity(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Item>
) {
    data class Item(
        val id: Int,
        val media: String,
        val title: String
    )
}