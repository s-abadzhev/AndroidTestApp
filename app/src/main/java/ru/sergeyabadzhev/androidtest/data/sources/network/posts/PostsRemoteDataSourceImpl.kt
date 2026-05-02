package ru.sergeyabadzhev.androidtest.data.sources.network.posts

import ru.sergeyabadzhev.androidtest.core.network.NetworkClientImpl
import ru.sergeyabadzhev.androidtest.data.sources.network.ApiEndpoint
import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostsRemoteDataSource

class PostsRemoteDataSourceImpl(private val client: NetworkClientImpl): PostsRemoteDataSource {
    override suspend fun fetchPosts(): List<PostDTO> {
        return client.request<List<PostDTO>>(ApiEndpoint.PostsList)
    }
}