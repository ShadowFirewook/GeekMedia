package com.example.geekmedia.data.mappers

import com.example.geekmedia.data.entities.NewsEntity
import com.example.geekmedia.domain.models.News


fun NewsEntity.toNews() = News(
    count,
//    next,
//    previous,
    results = results.map { it.toItem() }
)

fun News.toNewsEntity() = NewsEntity(
    count,
//    next,
//    previous,
    results = results.map { it.toItemEntity()}
)

fun NewsEntity.ItemEntity.toItem() = News.Item(
    category,
    created_date,
    created_date_time,
    description,
    id,
    image,
    title
)

fun News.Item.toItemEntity() = NewsEntity.ItemEntity(
    category,
    created_date,
    created_date_time,
    description,
    id,
    image,
    title
)