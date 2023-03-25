package com.example.geekmedia.domain.repositories

import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPost(id: Int) : Flow<Status<Post>>



}