package ru.sergeyabadzhev.androidtest.data.sources.local.posts

import kotlinx.coroutines.flow.Flow

interface PostsLocalDataSource {
    fun getAllAsFlow(): Flow<List<PostDbEntity>>
    suspend fun save(posts: List<PostDbEntity>)
}