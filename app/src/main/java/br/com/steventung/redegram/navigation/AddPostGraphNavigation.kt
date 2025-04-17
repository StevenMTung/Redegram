package br.com.steventung.redegram.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.steventung.redegram.ui.add_post.AddPostScreen

fun NavGraphBuilder.addPostGraph() {
    composable(route = AppDestination.AddPost.route) {
        AddPostScreen()
    }
}