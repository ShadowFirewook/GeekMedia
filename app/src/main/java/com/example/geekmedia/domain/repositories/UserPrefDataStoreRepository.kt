package com.example.geekmedia.domain.repositories

interface UserPrefDataStoreRepository {

    suspend fun saveRegisteredUser(isRegistered: Boolean)

}