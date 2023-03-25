package com.example.geekmedia.data.repositories

import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.geekmedia.core.REGISTERED_USER
import com.example.geekmedia.core.USER_AVATAR
import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.repositories.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPrefRepositoryImpl @Inject constructor(
    private val userPref: DataStore<Preferences>
): BaseRepository(), UserPrefRepository {


    //    override suspend fun saveRegisteredUser(isRegisteredUser: Boolean) {
//        userPref.edit {
//            it[REGISTERED_USER] = isRegisteredUser
//        }
//    }
//    override suspend fun saveUserAvatar(imageUri: Uri): Flow<Status<Uri>> = doRequest {
//        userPref.edit {
//            it[USER_AVATAR] = imageUri.toString()
//        }
//        imageUri
//    }
//
//    override suspend fun getUserAvatar(): Flow<Status<String>> = doRequest {
//        userPref.data.collect{
//            val userAvatar = it[USER_AVATAR]
//        }
//
//    }

}