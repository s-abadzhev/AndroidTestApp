package ru.sergeyabadzhev.androidtest.data.sources.local.posts

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Upsert
    suspend fun insert(item: PostDbEntity)

    @Query("SELECT count(*) FROM PostDbEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM PostDbEntity")
    fun getAllAsFlow(): Flow<List<PostDbEntity>>
}