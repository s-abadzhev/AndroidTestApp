package ru.sergeyabadzhev.androidtest.features.singlePost

import ru.sergeyabadzhev.androidtest.domain.models.Post

interface SinglePostComponent {
    val post: Post
    fun onBackClicked()
}