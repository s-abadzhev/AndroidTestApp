package ru.sergeyabadzhev.androidtest.features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform
import ru.sergeyabadzhev.androidtest.data.repository.posts.PostsRepository
import ru.sergeyabadzhev.androidtest.domain.models.Post
import ru.sergeyabadzhev.androidtest.features.postsList.PostsListComponent
import ru.sergeyabadzhev.androidtest.features.postsList.PostsListComponentImpl
import ru.sergeyabadzhev.androidtest.features.root.RootComponent.Child
import ru.sergeyabadzhev.androidtest.features.singlePost.SinglePostComponent
import ru.sergeyabadzhev.androidtest.features.singlePost.SinglePostComponentImpl

class RootComponentImpl(
    componentContext: ComponentContext,
    private val postsRepository: PostsRepository,
): RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.PostsList,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, childComponentContext: ComponentContext): Child =
        when(config) {
            is Config.PostsList -> Child.PostsList(postsListComponent(childComponentContext))
            is Config.SinglePost -> Child.SinglePost(singlePostComponent(childComponentContext, config.post))
        }

    private fun postsListComponent(componentContext: ComponentContext): PostsListComponent =
        PostsListComponentImpl(
            componentContext = componentContext,
            onShowSinglePost = { post -> navigation.pushNew(Config.SinglePost(post)) },
            postsRepository = postsRepository
        )

    private fun singlePostComponent(componentContext: ComponentContext, post: Post): SinglePostComponent =
        SinglePostComponentImpl(
            componentContext = componentContext,
            onBackPressed = navigation::pop,
            post = post,
        )

    @Serializable
    private sealed interface Config {
        @Serializable
        data object PostsList : Config
        @Serializable
        data class SinglePost(val post: Post) : Config
    }
}