package com.example.geekmedia.domain

sealed class Status <T> (
    val data: T? = null,
    val error: String? = null
)   {

    class Loading<T>: Status<T>()

    class Success<T>(data: T): Status<T>(
        data = data
    )

    class Error<T>(error: String): Status<T>(
        error = error
    )

}