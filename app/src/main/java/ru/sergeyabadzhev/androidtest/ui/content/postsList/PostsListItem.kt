package ru.sergeyabadzhev.androidtest.ui.content.postsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sergeyabadzhev.androidtest.domain.models.Post

@Composable
fun PostsListItem(
    post: Post,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize(),
        colors = CardColors(
            Color.White,
            Color.Blue,
            Color.LightGray,
            Color.Gray
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                post.title,
                color = Color.Black,
                fontSize = 20.sp,
                maxLines = 2,
            )
            Text(
                post.body,
                color = Color.Gray,
                fontSize = 16.sp,
                maxLines = 4,
            )
        }
    }
}