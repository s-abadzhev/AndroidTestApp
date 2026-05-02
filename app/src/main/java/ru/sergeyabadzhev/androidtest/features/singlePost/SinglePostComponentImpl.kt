package ru.sergeyabadzhev.androidtest.features.singlePost

import com.arkivanov.decompose.ComponentContext
import ru.sergeyabadzhev.androidtest.domain.models.Post

class SinglePostComponentImpl(
    componentContext: ComponentContext,
    override val post: Post,
    private val onBackPressed: () -> Unit,
) : SinglePostComponent, ComponentContext by componentContext {
    override fun onBackClicked() {
        onBackPressed()
    }
}