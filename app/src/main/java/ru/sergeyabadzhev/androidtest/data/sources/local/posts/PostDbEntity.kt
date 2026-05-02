package ru.sergeyabadzhev.androidtest.data.sources.local.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostDbEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
    val title: String,
    val body: String,
)