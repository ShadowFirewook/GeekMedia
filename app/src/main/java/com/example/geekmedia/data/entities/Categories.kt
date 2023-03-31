package com.example.geekmedia.data.entities

class Categories : ArrayList<Categories.Category>(){

    data class Category(
        val en_title: String,
        val id: Int,
        val ru_title: String
    )
}

