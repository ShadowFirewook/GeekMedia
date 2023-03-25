package com.example.geekmedia.domain.usecases

import com.example.geekmedia.domain.repositories.NewsRepository
import com.example.geekmedia.domain.repositories.UserPrefRepository
import javax.inject.Inject

class GetUserAvatarUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepository
) {
//    suspend fun getUserAvatar() = userPrefRepository.getUserAvatar()
}