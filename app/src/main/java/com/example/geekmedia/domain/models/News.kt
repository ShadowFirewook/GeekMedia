package com.example.geekmedia.domain.models

import com.example.geekmedia.core.DEFAULT_ID_FOR_POST

class News (
    val count: Int,
//    val next: Any,
//    val previous: Any,
    val results: List<Item>
    ) {
        data class Item(
            val category: String,
            val created_date: String,
            val created_date_time: String,
            val description: String,
            val id: Int = DEFAULT_ID_FOR_POST,
            val image: String,
            val title: String
        ) {
//            constructor() : this("","","","",0,"","")
        }
}