package com.example.geekmedia.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.geekmedia.core.LIKED_NEWS
import com.example.geekmedia.core.USER_DATA_STORE
import com.example.geekmedia.data.mappers.toNews
import com.example.geekmedia.data.remote.ApiService
import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.News
import com.example.geekmedia.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val likeNewsPrefDataStore: DataStore<Preferences>
//    private val favoritesNews: ArrayList<News>,
//    private val favoritePost: ArrayList<News>
    ) : BaseRepository(),NewsRepository {

    override fun getNews(/*posts: Posts,category: String,createdDate: String,
        page:Int,
        pageSize: Int*/
    ): Flow<Status<News>> = doRequest {
        apiService.getNews(/*category,createdDate,page,pageSize*/).body()!!.toNews()
    }

    override fun likeNews(isLiked:Boolean) = doRequest {
        likeNewsPrefDataStore.edit {
            it[LIKED_NEWS] = isLiked
        }
    }

    override fun getFavoriteNews(
        favoritesNews: ArrayList<News.Item>
    ): Flow<Status<ArrayList<News.Item>>> = doRequest {
        favoritesNews
    }

}