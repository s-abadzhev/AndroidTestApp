package ru.sergeyabadzhev.androidtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import ru.sergeyabadzhev.androidtest.features.root.RootComponentImpl
import ru.sergeyabadzhev.androidtest.ui.content.root.RootContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = RootComponentImpl(componentContext = defaultComponentContext())

        enableEdgeToEdge()
        setContent {
            RootContent(root)
        }
    }
}
