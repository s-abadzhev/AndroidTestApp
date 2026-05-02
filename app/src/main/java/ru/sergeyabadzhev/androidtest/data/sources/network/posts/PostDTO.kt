package ru.sergeyabadzhev.androidtest.data.sources.network.posts

import kotlinx.serialization.Serializable

@Serializable
data class PostDTO (
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
}