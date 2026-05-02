package ru.sergeyabadzhev.androidtest.core.network

import ru.sergeyabadzhev.androidtest.data.sources.network.ApiEndpoint
import kotlin.reflect.KClass

interface NetworkClient {
    suspend fun <T : Any> request(endpoint: ApiEndpoint, clazz: KClass<T>): T
}

suspend inline fun <reified T> NetworkClient.request(endpoint: ApiEndpoint): T {
    return request(endpoint, T::class as KClass<*>) as T
}