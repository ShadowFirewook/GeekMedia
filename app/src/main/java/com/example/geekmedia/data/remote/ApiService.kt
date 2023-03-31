package com.example.geekmedia.data.remote

import com.example.geekmedia.data.entities.MultiMediaEntity
import com.example.geekmedia.data.entities.PostEntity
import com.example.geekmedia.data.entities.NewsEntity
import com.example.geekmedia.data.entities.UserDataEntity
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("media")
    suspend fun getMedia(
        @Query("page") page :Int,
        @Query("page_size") pageSize :Int
    ) : Response<MultiMediaEntity>

    @GET("v1/posts/")
   suspend fun getNews(
//        @Query("category") category:String,
//        @Query("created_date") createdDate :String,
//       @Query("page") page :Int,
//       @Query("page_size") pageSize :Int
    ) : Response<NewsEntity>

    @GET("v1/posts/{id}")
    suspend fun getPost(
        @Path("id") id: Int
    ) : Response<PostEntity>

    @POST("auth/registration/")
    suspend fun registerUser(
        @Body userDataEntity: UserDataEntity
    ) : Response<UserDataEntity>

}