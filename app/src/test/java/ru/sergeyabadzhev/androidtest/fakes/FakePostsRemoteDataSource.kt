package ru.sergeyabadzhev.androidtest.fakes

import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostDTO
import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostsRemoteDataSource

class FakePostsRemoteDataSource : PostsRemoteDataSource {
    var posts: List<PostDTO> = emptyList()
    var shouldThrow: Exception? = null

    override suspend fun fetchPosts(): List<PostDTO> {
        shouldThrow?.let { throw it }
        return posts
    }
}
