package ru.sergeyabadzhev.androidtest.data.sources.network.posts

interface PostsRemoteDataSource {
    suspend fun fetchPosts(): List<PostDTO>
}