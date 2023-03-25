package com.example.geekmedia.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.geekmedia.core.BASE_URL
import com.example.geekmedia.core.USER_DATA_STORE
import com.example.geekmedia.data.local.FavoritesNewsDao
import com.example.geekmedia.data.local.FavoritesNewsDatabase
import com.example.geekmedia.data.remote.ApiService
import com.example.geekmedia.data.repositories.*
import com.example.geekmedia.domain.repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit (okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideNewsRepository(
        apiService: ApiService
    ) : NewsRepository = NewsRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun providePostRepository(
        apiService: ApiService
    ) : PostRepository = PostRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideUserDataRepository(
        apiService: ApiService
    ) : UserDataRepository = UserDataRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideUserPrefDataStoreRepository(
        userPrefDataStore: DataStore<Preferences>
    ): UserPrefRepository = UserPrefRepositoryImpl(userPrefDataStore)

//    @Singleton
//    @Provides
//    fun provideUserPrefDataStore(
//        @ApplicationContext context: Context
//    ): DataStore<Preferences> = PreferenceDataStoreFactory
//        .create(produceFile = {context.preferencesDataStoreFile(
//        USER_DATA_STORE)})

    @Singleton
    @Provides
    fun provideUserPref(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory
        .create(produceFile = {context.preferencesDataStoreFile(
            USER_DATA_STORE)})

    @Singleton
    @Provides
    fun provideFavoritesNewsDatabase(
        @ApplicationContext context: Context
    ): FavoritesNewsDatabase {
        return Room.databaseBuilder(
            context,
            FavoritesNewsDatabase::class.java,
            "favorites_news_db"
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideFavoritesNewsDao(favoritesNewsDatabase: FavoritesNewsDatabase) = favoritesNewsDatabase.favoritesNewsDao()

    @Singleton
    @Provides
    fun provideFavoritesNewsRepository(favoritesNewsDao: FavoritesNewsDao): FavoritesNewsRepository = FavoritesNewsRepositoryImpl(favoritesNewsDao)

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

}