package ru.sergeyabadzhev.androidtest.features.postsList

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.sergeyabadzhev.androidtest.core.network.NetworkError
import ru.sergeyabadzhev.androidtest.domain.models.Post
import ru.sergeyabadzhev.androidtest.fakes.FakePostsRepository

@OptIn(ExperimentalCoroutinesApi::class)
class PostsListComponentImplTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createComponent(
        repository: FakePostsRepository = FakePostsRepository(),
        onShowSinglePost: (Post) -> Unit = {},
    ): PostsListComponentImpl {
        val lifecycle = LifecycleRegistry()
        val context = DefaultComponentContext(lifecycle)
        lifecycle.resume()
        return PostsListComponentImpl(
            componentContext = context,
            onShowSinglePost = onShowSinglePost,
            postsRepository = repository,
        )
    }

    @Test
    fun `initial state has empty posts list`() {
        val component = createComponent()
        assertEquals(emptyList<Post>(), component.state.value.posts)
    }

    @Test
    fun `initial state is not loading`() {
        val component = createComponent()
        assertFalse(component.state.value.isLoading)
    }

    @Test
    fun `initial state has no error`() {
        val component = createComponent()
        assertNull(component.state.value.error)
    }

    @Test
    fun `posts from repository appear in state`() = runTest {
        val repository = FakePostsRepository()
        val component = createComponent(repository)

        repository.emitPosts(listOf(Post(id = 1, userId = 1, title = "Title", body = "Body")))

        assertEquals(1, component.state.value.posts.size)
        assertEquals("Title", component.state.value.posts[0].title)
    }

    @Test
    fun `state updates when repository emits new posts`() = runTest {
        val repository = FakePostsRepository()
        val component = createComponent(repository)

        repository.emitPosts(listOf(Post(id = 1, userId = 1, title = "First", body = "")))
        assertEquals(1, component.state.value.posts.size)

        repository.emitPosts(listOf(
            Post(id = 1, userId = 1, title = "First", body = ""),
            Post(id = 2, userId = 1, title = "Second", body = ""),
        ))
        assertEquals(2, component.state.value.posts.size)
    }

    @Test
    fun `onAppear sets isLoading true then false on success`() = runTest {
        val component = createComponent()
        component.onAppear()
        assertFalse(component.state.value.isLoading)
    }

    @Test
    fun `onAppear clears previous error on success`() = runTest {
        val errorRepository = FakePostsRepository(refreshError = NetworkError.NoInternetConnection())
        val component = createComponent(errorRepository)
        component.onAppear()

        val successRepository = FakePostsRepository()
        val freshComponent = createComponent(successRepository)
        freshComponent.onAppear()

        assertNull(freshComponent.state.value.error)
    }


    @Test
    fun `NoInternetConnection error sets NoInternetConnectionError state`() = runTest {
        val component = createComponent(FakePostsRepository(NetworkError.NoInternetConnection()))
        component.onAppear()
        assertEquals(PostsListError.NoInternetConnectionError, component.state.value.error)
    }

    @Test
    fun `Timeout error sets NoInternetConnectionError state`() = runTest {
        val component = createComponent(FakePostsRepository(NetworkError.Timeout()))
        component.onAppear()
        assertEquals(PostsListError.NoInternetConnectionError, component.state.value.error)
    }

    @Test
    fun `unknown network error sets NetworkError state`() = runTest {
        val cause = RuntimeException("Something went wrong")
        val component = createComponent(FakePostsRepository(NetworkError.Unknown(cause)))
        component.onAppear()
        assertEquals(PostsListError.NetworkError, component.state.value.error)
    }

    @Test
    fun `error state is cleared when isLoading becomes true`() = runTest {
        val repository = FakePostsRepository(NetworkError.NoInternetConnection())
        val component = createComponent(repository)
        component.onAppear()
        assertEquals(PostsListError.NoInternetConnectionError, component.state.value.error)
        assertTrue(component.state.value.isLoading.not())
    }

    @Test
    fun `onPostClicked invokes callback with correct post`() {
        val clicked = mutableListOf<Post>()
        val component = createComponent(onShowSinglePost = { clicked.add(it) })
        val post = Post(id = 1, userId = 1, title = "Title", body = "Body")

        component.onPostClicked(post)

        assertEquals(1, clicked.size)
        assertEquals(post, clicked[0])
    }

    @Test
    fun `onPostClicked passes exact post reference`() {
        var received: Post? = null
        val component = createComponent(onShowSinglePost = { received = it })
        val post = Post(id = 42, userId = 7, title = "Exact", body = "Match")

        component.onPostClicked(post)

        assertEquals(42, received?.id)
        assertEquals(7, received?.userId)
    }
}
