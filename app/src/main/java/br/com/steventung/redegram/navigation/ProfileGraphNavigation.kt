package br.com.steventung.redegram.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.steventung.redegram.ui.profile.ProfileScreen

fun NavGraphBuilder.profileGraph() {
    composable(route = AppDestination.Profile.route) {
        ProfileScreen()
    }
}