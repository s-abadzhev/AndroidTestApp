package ru.sergeyabadzhev.androidtest.core.localStorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostDao
import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostDbEntity


@Database(entities = [PostDbEntity::class], version = 1)
abstract class RoomDataBase: RoomDatabase() {
    abstract fun getPostsDao(): PostDao
}

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<RoomDataBase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("room.db")
    return Room.databaseBuilder<RoomDataBase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

fun getDatabase(context: Context): RoomDataBase {
    return getDatabaseBuilder(context).build()
}