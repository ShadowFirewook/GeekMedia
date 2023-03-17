package com.example.geekmedia.domain.usecases

import com.example.geekmedia.domain.repositories.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private var postRepository: PostRepository
) {

    fun getPost(id:Int) = postRepository.getPost(id)

}