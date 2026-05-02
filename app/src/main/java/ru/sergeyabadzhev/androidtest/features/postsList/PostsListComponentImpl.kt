package ru.sergeyabadzhev.androidtest.features.postsList

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform
import ru.sergeyabadzhev.androidtest.core.network.NetworkError
import ru.sergeyabadzhev.androidtest.data.repository.posts.PostsRepository
import ru.sergeyabadzhev.androidtest.domain.models.Post


class PostsListComponentImpl(
    componentContext: ComponentContext,
    private val onShowSinglePost: (Post) -> Unit,
    private val postsRepository: PostsRepository = KoinPlatform.getKoin().get<PostsRepository>(),
): PostsListComponent, ComponentContext by componentContext {

    private val _state = MutableValue(PostsListState())
    override val state = _state
    private var updateJob: Job? = null

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main.immediate)

    init {
        coroutineScope.launch {
            postsRepository.getPosts().collect { posts ->
                _state.update { it.copy(posts = posts) }
            }
        }
    }

    override fun onAppear() {
        updatePostsList()
    }

    override fun updatePostsList() {
        updateJob?.cancel()
        _state.update { it.copy(isLoading = true) }
        updateJob = coroutineScope.launch {
            try {
                postsRepository.refresh()
                _state.update { it.copy(isLoading = false) }
            } catch (e: NetworkError) {
                _state.update { s ->
                    when (e) {
                        is NetworkError.Timeout -> s.copy(isLoading = false, error = PostsListError.NoInternetConnectionError)
                        is NetworkError.NoInternetConnection -> s.copy(isLoading = false, error = PostsListError.NoInternetConnectionError)
                        else -> s.copy(isLoading = false, error = PostsListError.NetworkError)
                    }
                }
            }
        }
    }


    override fun onPostClicked(post: Post) {
        onShowSinglePost(post)
    }
}