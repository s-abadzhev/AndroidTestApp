package ru.sergeyabadzhev.androidtest.data.sources.network.posts

import ru.sergeyabadzhev.androidtest.core.network.NetworkClient
import ru.sergeyabadzhev.androidtest.core.network.request
import ru.sergeyabadzhev.androidtest.data.sources.network.ApiEndpoint

class PostsRemoteDataSourceImpl(private val client: NetworkClient): PostsRemoteDataSource {
    override suspend fun fetchPosts(): List<PostDTO> {
        return client.request<List<PostDTO>>(ApiEndpoint.PostsList)
    }
}