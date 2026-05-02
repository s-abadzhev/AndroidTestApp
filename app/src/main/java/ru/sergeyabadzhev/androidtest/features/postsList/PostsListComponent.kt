package ru.sergeyabadzhev.androidtest.features.postsList

import com.arkivanov.decompose.value.Value
import ru.sergeyabadzhev.androidtest.domain.models.Post

enum class PostsListError {
    NetworkError,
    NoInternetConnectionError,
}

data class PostsListState (
    val isLoading: Boolean = false,
    val posts: List<Post> = listOf(),
    val error: PostsListError? = null,
)
interface PostsListComponent {
    val state: Value<PostsListState>
    fun onAppear()
    fun updatePostsList()
    fun onPostClicked(post: Post)
}