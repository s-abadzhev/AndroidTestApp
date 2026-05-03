package ru.sergeyabadzhev.androidtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import org.koin.android.ext.android.inject
import ru.sergeyabadzhev.androidtest.data.repository.posts.PostsRepository
import ru.sergeyabadzhev.androidtest.features.root.RootComponentImpl
import ru.sergeyabadzhev.androidtest.ui.content.root.RootContent

class MainActivity : ComponentActivity() {

    private val postsRepository: PostsRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = RootComponentImpl(
            componentContext = defaultComponentContext(),
            postsRepository = postsRepository,
            )

        enableEdgeToEdge()
        setContent {
            RootContent(root)
        }
    }
}
