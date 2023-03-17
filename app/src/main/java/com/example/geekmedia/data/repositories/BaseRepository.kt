package com.example.geekmedia.data.repositories

import com.example.geekmedia.domain.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    protected fun<T> doRequest(request: suspend () -> T) = flow {
        emit(Status.Loading())
        delay(10)
        try {
            val data = request()
            emit(Status.Success(data))
        } catch (ioException: IOException) {
            emit(Status.Error(ioException.localizedMessage ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

}