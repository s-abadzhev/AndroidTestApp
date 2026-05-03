package ru.sergeyabadzhev.androidtest.data.repository.posts

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import ru.sergeyabadzhev.androidtest.core.network.NetworkError
import ru.sergeyabadzhev.androidtest.data.sources.network.posts.PostDTO
import ru.sergeyabadzhev.androidtest.fakes.FakePostsLocalDataSource
import ru.sergeyabadzhev.androidtest.fakes.FakePostsRemoteDataSource

class PostsRepositoryImplTest {

    private lateinit var fakeRemote: FakePostsRemoteDataSource
    private lateinit var fakeLocal: FakePostsLocalDataSource
    private lateinit var repository: PostsRepositoryImpl

    @Before
    fun setup() {
        fakeRemote = FakePostsRemoteDataSource()
        fakeLocal = FakePostsLocalDataSource()
        repository = PostsRepositoryImpl(fakeRemote, fakeLocal)
    }

    @Test
    fun `getPosts emits empty list initially`() = runTest {
        val posts = repository.getPosts().first()
        assertEquals(emptyList<Any>(), posts)
    }

    @Test
    fun `getPosts emits posts saved in local source`() = runTest {
        fakeRemote.posts = listOf(PostDTO(id = 1, userId = 1, title = "Title", body = "Body"))
        repository.refresh()

        val posts = repository.getPosts().first()
        assertEquals(1, posts.size)
        assertEquals(1, posts[0].id)
        assertEquals("Title", posts[0].title)
    }

    @Test
    fun `getPosts maps domain model fields correctly`() = runTest {
        fakeRemote.posts = listOf(PostDTO(id = 7, userId = 3, title = "Hello", body = "World"))
        repository.refresh()

        val post = repository.getPosts().first().first()
        assertEquals(7, post.id)
        assertEquals(3, post.userId)
        assertEquals("Hello", post.title)
        assertEquals("World", post.body)
    }

    @Test
    fun `refresh saves all fetched posts to local source`() = runTest {
        fakeRemote.posts = listOf(
            PostDTO(id = 1, userId = 1, title = "First", body = "Body 1"),
            PostDTO(id = 2, userId = 1, title = "Second", body = "Body 2"),
        )
        repository.refresh()

        assertEquals(2, fakeLocal.savedPosts.size)
    }

    @Test
    fun `refresh replaces previous local data`() = runTest {
        fakeRemote.posts = listOf(PostDTO(id = 1, userId = 1, title = "Old", body = "Old body"))
        repository.refresh()

        fakeRemote.posts = listOf(PostDTO(id = 2, userId = 2, title = "New", body = "New body"))
        repository.refresh()

        val posts = repository.getPosts().first()
        assertEquals(1, posts.size)
        assertEquals("New", posts[0].title)
    }

    @Test
    fun `refresh propagates NoInternetConnection error`() {
        fakeRemote.shouldThrow = NetworkError.NoInternetConnection()

        assertThrows(NetworkError.NoInternetConnection::class.java) {
            runTest { repository.refresh() }
        }
    }

    @Test
    fun `refresh propagates Timeout error`() {
        fakeRemote.shouldThrow = NetworkError.Timeout()

        assertThrows(NetworkError.Timeout::class.java) {
            runTest { repository.refresh() }
        }
    }
}
