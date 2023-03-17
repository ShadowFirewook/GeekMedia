package com.example.geekmedia.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.geekmedia.core.REGISTERED_USER
import com.example.geekmedia.domain.repositories.UserPrefDataStoreRepository
import javax.inject.Inject

class UserPrefDataStoreRepositoryImpl @Inject constructor(
    private val userPrefDataStore: DataStore<Preferences>
): BaseRepository(), UserPrefDataStoreRepository {

    override suspend fun saveRegisteredUser(isRegisteredUser: Boolean) {
        userPrefDataStore.edit {
            it[REGISTERED_USER] = isRegisteredUser
        }
    }

}