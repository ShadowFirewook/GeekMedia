package com.example.geekmedia.data.entities

class Reclam : ArrayList<Reclam.ReclamItem>() {

    data class ReclamItem(
        val id: Int,
        val url: String,
        val video: String
    )

}