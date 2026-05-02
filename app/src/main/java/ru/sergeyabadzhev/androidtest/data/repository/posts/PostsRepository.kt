package ru.sergeyabadzhev.androidtest.data.repository.posts

import kotlinx.coroutines.flow.Flow
import ru.sergeyabadzhev.androidtest.domain.models.Post

interface PostsRepository {
    fun getPosts(): Flow<List<Post>>
    suspend fun refresh()
}