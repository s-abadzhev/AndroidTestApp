package ru.sergeyabadzhev.androidtest.ui.content.singleList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sergeyabadzhev.androidtest.features.singlePost.SinglePostComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SinglePostContent(
    component: SinglePostComponent,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    maxLines = 1,
                    text = component.post.title
                ) },
                navigationIcon = {
                    IconButton(onClick = component::onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back button",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(8.dp).verticalScroll(scrollState)
        ) {
            Text(
                component.post.title,
                color = Color.Black,
                fontSize = 20.sp,
            )
            Text(
                component.post.body,
                color = Color.Gray,
                fontSize = 16.sp,
            )
        }
    }
}