package com.example.geekmedia.data.remote

import com.example.geekmedia.data.entities.MultiMedia
import com.example.geekmedia.data.entities.Post
import com.example.geekmedia.data.entities.Posts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("media")
    suspend fun getMedia(
        @Query("page") page :Int,
        @Query("page_size") pageSize :Int
    ) : Response<MultiMedia>

    @GET("posts")
    fun getPosts(
        @Query("category") category:String,
        @Query("created_date") createdDate :String,
        @Query("page") page :Int,
        @Query("page_size") pageSize :Int
    ) : Response<Posts>

    @GET("posts/{id}")
    fun getUser(
        @Path("id") id: Int
    ) : Response<Post>


}