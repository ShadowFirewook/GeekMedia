package com.example.geekmedia.domain.repositories

import com.example.geekmedia.domain.Status
import com.example.geekmedia.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    fun registerUser( userData: UserData) : Flow<Status<UserData>>

}