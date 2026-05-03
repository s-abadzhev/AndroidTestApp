package ru.sergeyabadzhev.androidtest.fakes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostDbEntity
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostsLocalDataSource

class FakePostsLocalDataSource : PostsLocalDataSource {
    private val flow = MutableStateFlow<List<PostDbEntity>>(emptyList())

    val savedPosts: List<PostDbEntity> get() = flow.value

    override fun getAllAsFlow(): Flow<List<PostDbEntity>> = flow

    override suspend fun save(posts: List<PostDbEntity>) {
        flow.value = posts
    }
}
