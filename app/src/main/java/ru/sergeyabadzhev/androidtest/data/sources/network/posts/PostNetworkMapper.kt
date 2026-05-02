package ru.sergeyabadzhev.androidtest.data.sources.network.posts

import ru.sergeyabadzhev.androidtest.data.sources.local.posts.PostDbEntity
import ru.sergeyabadzhev.androidtest.domain.models.Post

object PostNetworkMapper {

    fun toDbEntity(response: PostDTO): PostDbEntity {
        return PostDbEntity(
            userId = response.userId,
            id = response.id,
            title = response.title,
            body = response.body
        )
    }
}