package br.com.steventung.redegram.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.steventung.redegram.ui.searching.SearchingScreen

fun NavGraphBuilder.searchingGraph() {
    composable(route = AppDestination.Search.route) {
        SearchingScreen()
    }
}