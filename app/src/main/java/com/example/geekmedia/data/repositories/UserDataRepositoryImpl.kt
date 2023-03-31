package com.example.geekmedia.data.repositories

import com.example.geekmedia.data.mappers.toUserData
import com.example.geekmedia.data.mappers.toUserDataEntity
import com.example.geekmedia.data.remote.ApiService
import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.UserData
import com.example.geekmedia.domain.repositories.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): BaseRepository(),UserDataRepository {

    override fun registerUser(
        userData: UserData
    ): Flow<Status<UserData>> = doRequest {
        apiService.registerUser(userData.toUserDataEntity()).body()!!.toUserData()
    }
}