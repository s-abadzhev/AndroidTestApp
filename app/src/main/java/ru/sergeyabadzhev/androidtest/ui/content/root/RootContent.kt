package ru.sergeyabadzhev.androidtest.ui.content.root

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.sergeyabadzhev.androidtest.features.root.RootComponent
import ru.sergeyabadzhev.androidtest.ui.content.postsList.PostsListContent
import ru.sergeyabadzhev.androidtest.ui.content.singleList.SinglePostContent
import ru.sergeyabadzhev.androidtest.ui.theme.AndroidTestTheme


@Composable
fun RootContent(
    component: RootComponent,
) {
    AndroidTestTheme {
        Scaffold(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)) { innerPadding ->
            Children(
                stack = component.stack,
                modifier = Modifier.padding(innerPadding),
                animation = stackAnimation { fade() + scale() }
            ) {
                when (val instance = it.instance) {
                    is RootComponent.Child.PostsList -> PostsListContent(component = instance.component)
                    is RootComponent.Child.SinglePost -> SinglePostContent(component = instance.component)
                }
            }
        }
    }
}