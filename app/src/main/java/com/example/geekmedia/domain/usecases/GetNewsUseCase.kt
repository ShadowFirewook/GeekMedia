package com.example.geekmedia.domain.usecases

import com.example.geekmedia.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    fun getNews() = newsRepository.getNews()

}