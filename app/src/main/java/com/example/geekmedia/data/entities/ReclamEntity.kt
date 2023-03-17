package com.example.geekmedia.data.entities

class ReclamEntity : ArrayList<ReclamEntity.ReclamItem>() {

    data class ReclamItem(
        val id: Int,
        val url: String,
        val video: String
    )

}