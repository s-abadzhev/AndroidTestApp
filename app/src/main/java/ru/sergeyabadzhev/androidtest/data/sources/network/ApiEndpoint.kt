package ru.sergeyabadzhev.androidtest.data.sources.network

const val BASE_URL = "https://jsonplaceholder.typicode.com"
sealed class ApiEndpoint {
    object PostsList : ApiEndpoint()

    val url: String
        get() = when (this) {
            is PostsList -> buildString {
                append(BASE_URL)
                append("/posts")
            }
        }
}