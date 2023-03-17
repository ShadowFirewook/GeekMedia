package com.example.geekmedia.domain.repositories

import androidx.datastore.preferences.core.Preferences
import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(/*posts: Posts,category: String,createdDate: String, page:Int, pageSize: Int*/ ) : Flow<Status<News>>

    fun likeNews(isLiked:Boolean): Flow<Status<Preferences>>

    fun getFavoriteNews(favoritesNews: ArrayList<News.Item>) : Flow<Status<ArrayList<News.Item>>>

}