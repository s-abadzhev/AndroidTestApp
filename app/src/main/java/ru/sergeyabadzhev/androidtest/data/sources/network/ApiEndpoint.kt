package ru.sergeyabadzhev.androidtest.data.sources.network

import kotlinx.serialization.Serializable

const val BASE_URL = "https://jsonplaceholder.typicode.com"

enum class Method {
    GET,
    POST,
    PUT,
    DELETE
}

sealed class ApiEndpoint {
    abstract val url: String
    abstract val methodType: Method
    abstract val body: @Serializable Any?
    object GetPostsList : ApiEndpoint() {
        override val url = "$BASE_URL/posts"
        override val methodType = Method.GET
        override val body = null
    }

}