package com.example.geekmedia.domain.repositories

import androidx.datastore.preferences.core.Preferences
import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews() : Flow<Status<News>>


}