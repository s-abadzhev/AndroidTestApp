package ru.sergeyabadzhev.androidtest.di

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single
import ru.sergeyabadzhev.androidtest.core.localStorage.RoomDataBase
import ru.sergeyabadzhev.androidtest.core.localStorage.getDatabase
import ru.sergeyabadzhev.androidtest.core.network.NetworkClientImpl
import ru.sergeyabadzhev.androidtest.data.repository.posts.PostsRepository
import ru.sergeyabadzhev.androidtest.data.repository.posts.PostsRepositoryImpl
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostsLocalDataSource
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostsLocalDataSourceImpl
import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostsRemoteDataSource
import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostsRemoteDataSourceImpl

val mainModule = module {
    single<RoomDataBase> { getDatabase(get()) }
    single<NetworkClientImpl>()
    single<PostsRepositoryImpl>() bind PostsRepository::class
    single<PostsRemoteDataSourceImpl>() bind PostsRemoteDataSource::class
    single<PostsLocalDataSourceImpl>() bind PostsLocalDataSource::class
}