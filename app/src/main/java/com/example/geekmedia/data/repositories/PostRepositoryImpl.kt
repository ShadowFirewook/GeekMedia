package com.example.geekmedia.data.repositories

import com.example.geekmedia.data.mappers.toPost
import com.example.geekmedia.data.remote.ApiService
import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.Post
import com.example.geekmedia.domain.repositories.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): BaseRepository(), PostRepository {

    override fun getPost(id:Int): Flow<Status<Post>> = doRequest{
        apiService.getPost(id).body()!!.toPost()
    }

}