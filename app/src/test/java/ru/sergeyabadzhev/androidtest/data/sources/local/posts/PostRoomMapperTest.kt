package ru.sergeyabadzhev.androidtest.data.sources.local.posts

import org.junit.Assert.assertEquals
import org.junit.Test

class PostRoomMapperTest {

    private val entity = PostDbEntity(id = 1, userId = 2, title = "Test title", body = "Test body")

    @Test
    fun `toDomain maps id correctly`() {
        val post = PostRoomMapper.toDomain(entity)
        assertEquals(entity.id, post.id)
    }

    @Test
    fun `toDomain maps userId correctly`() {
        val post = PostRoomMapper.toDomain(entity)
        assertEquals(entity.userId, post.userId)
    }

    @Test
    fun `toDomain maps title correctly`() {
        val post = PostRoomMapper.toDomain(entity)
        assertEquals(entity.title, post.title)
    }

    @Test
    fun `toDomain maps body correctly`() {
        val post = PostRoomMapper.toDomain(entity)
        assertEquals(entity.body, post.body)
    }
}
