package ru.sergeyabadzhev.androidtest.data.sources.local.posts

import ru.sergeyabadzhev.androidtest.domain.models.Post

object PostRoomMapper {

    fun toDomain(entity: PostDbEntity): Post {
        return Post(
            userId = entity.userId,
            id = entity.id,
            title = entity.title,
            body = entity.body
        )
    }
}