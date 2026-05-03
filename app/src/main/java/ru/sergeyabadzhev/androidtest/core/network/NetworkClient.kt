package ru.sergeyabadzhev.androidtest.core.network

import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo
import ru.sergeyabadzhev.androidtest.data.sources.network.ApiEndpoint
import kotlin.reflect.KClass

interface NetworkClient {
    suspend fun <T : Any> request(endpoint: ApiEndpoint, typeInfo: TypeInfo): T
    fun close()
}

suspend inline fun <reified T: Any> NetworkClient.request(endpoint: ApiEndpoint): T {
    return request(endpoint, typeInfo<T>())
}