package ru.sergeyabadzhev.androidtest.fakes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sergeyabadzhev.androidtest.core.network.NetworkError
import ru.sergeyabadzhev.androidtest.data.repository.posts.PostsRepository
import ru.sergeyabadzhev.androidtest.domain.models.Post

class FakePostsRepository(
    private val refreshError: NetworkError? = null,
) : PostsRepository {
    private val postsFlow = MutableStateFlow<List<Post>>(emptyList())

    fun emitPosts(posts: List<Post>) {
        postsFlow.value = posts
    }

    override fun getPosts(): Flow<List<Post>> = postsFlow

    override suspend fun refresh() {
        refreshError?.let { throw it }
    }
}
