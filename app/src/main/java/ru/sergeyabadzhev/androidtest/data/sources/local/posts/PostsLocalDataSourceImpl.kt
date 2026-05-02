package ru.sergeyabadzhev.androidtest.data.sources.local.posts

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.sergeyabadzhev.androidtest.core.localStorage.RoomDataBase

class PostsLocalDataSourceImpl(private val db: RoomDataBase): PostsLocalDataSource {

    private val postsDao: PostDao by lazy {
        db.getPostsDao()
    }

    override fun getAllAsFlow(): Flow<List<PostDbEntity>> {
        return postsDao.getAllAsFlow()
    }

    override suspend fun save(posts: List<PostDbEntity>) {
        posts.forEach { post ->
           postsDao.insert(post)
        }
    }
}