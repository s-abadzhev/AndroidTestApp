package ru.sergeyabadzhev.androidtest.data.sources.network.posts

import org.junit.Assert.assertEquals
import org.junit.Test

class PostNetworkMapperTest {

    private val dto = PostDTO(id = 1, userId = 2, title = "Test title", body = "Test body")

    @Test
    fun `toDbEntity maps id correctly`() {
        val entity = PostNetworkMapper.toDbEntity(dto)
        assertEquals(dto.id, entity.id)
    }

    @Test
    fun `toDbEntity maps userId correctly`() {
        val entity = PostNetworkMapper.toDbEntity(dto)
        assertEquals(dto.userId, entity.userId)
    }

    @Test
    fun `toDbEntity maps title correctly`() {
        val entity = PostNetworkMapper.toDbEntity(dto)
        assertEquals(dto.title, entity.title)
    }

    @Test
    fun `toDbEntity maps body correctly`() {
        val entity = PostNetworkMapper.toDbEntity(dto)
        assertEquals(dto.body, entity.body)
    }
}
