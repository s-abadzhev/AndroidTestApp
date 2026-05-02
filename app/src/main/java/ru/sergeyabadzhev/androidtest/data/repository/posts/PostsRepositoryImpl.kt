package ru.sergeyabadzhev.androidtest.data.repository.posts

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostRoomMapper
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostsLocalDataSource
import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostNetworkMapper
import ru.sergeyabadzhev.androidtest.domain.models.Post
import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostsRemoteDataSource

class PostsRepositoryImpl(
    private val remoteDataSource: PostsRemoteDataSource,
    private val localDataSource: PostsLocalDataSource
    ): PostsRepository {

    override fun getPosts(): Flow<List<Post>> = localDataSource
        .getAllAsFlow()
        .map { list -> list.map { PostRoomMapper.toDomain(it) } }
        .flowOn(Dispatchers.IO)

    override suspend fun refresh() = withContext(Dispatchers.IO){
        val response = remoteDataSource.fetchPosts()
        val posts = response.map { PostNetworkMapper.toDbEntity(it) }
        localDataSource.save(posts)
    }
}