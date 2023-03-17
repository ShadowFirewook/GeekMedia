package com.example.geekmedia.data.mappers

import com.example.geekmedia.data.entities.PostEntity
import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.models.Post

fun Post.toNewsItem() = News.Item(
    category = category.ru_title,
    created_date = created_date,
    created_date_time = created_date_time,
    description,
    id,
    image,
    title
)

fun PostEntity.toPost() = Post(
    id,
    articles = articles.map { Post.Article(description, image, title)},
    category.toCategory(),
    created_date,
    created_date_time,
    description,
    image,
    title
)

private fun PostEntity.CategoryEntity.toCategory() = Post.Category(
    en_title = en_title,
    ru_title = ru_title
)

fun Post.toPostEntity() = PostEntity(
    articles.map { PostEntity.ArticleEntity(description, image, title)},
    category.toCategoryEntity(),
    created_date,
    created_date_time,
    description,
    id,
    image,
    title
)

private fun Post.Category.toCategoryEntity() = PostEntity.CategoryEntity(
    en_title = en_title,
    ru_title = ru_title
)

