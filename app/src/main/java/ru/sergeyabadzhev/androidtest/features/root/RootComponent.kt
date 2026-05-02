package ru.sergeyabadzhev.androidtest.features.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sergeyabadzhev.androidtest.features.postsList.PostsListComponent
import ru.sergeyabadzhev.androidtest.features.singlePost.SinglePostComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class PostsList(val component: PostsListComponent): Child()
        class SinglePost(val component: SinglePostComponent): Child()
    }
}