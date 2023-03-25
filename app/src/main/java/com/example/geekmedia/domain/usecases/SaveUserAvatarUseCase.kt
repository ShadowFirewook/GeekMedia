package com.example.geekmedia.domain.usecases

import android.net.Uri
import com.example.geekmedia.domain.repositories.NewsRepository
import com.example.geekmedia.domain.repositories.UserPrefRepository
import javax.inject.Inject

class SaveUserAvatarUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepository
) {
//    suspend fun saveUserAvatar(imageUri: Uri) = userPrefRepository.saveUserAvatar(imageUri)
}