package ru.sergeyabadzhev.androidtest.ui.content.postsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sergeyabadzhev.androidtest.features.postsList.PostsListComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PostsListContent(
    component: PostsListComponent,
) {
    LaunchedEffect(Unit) {
        component.onAppear()
    }

    val state by component.state.subscribeAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Posts") },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PullToRefreshBox(
                isRefreshing = state.isLoading,
                onRefresh = {
                    component.updatePostsList()
                }) {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.posts) {
                        PostsListItem(
                            it,
                            Modifier.clickable { component.onPostClicked(it) }
                        )
                    }
                }
            }
        }
    }
}