package br.com.steventung.redegram.navigation

sealed class AppDestination(val route: String) {
    data object Home: AppDestination("homeGraphRoute")
    data object Search: AppDestination("searchGraphRoute")
    data object AddPost: AppDestination("addPostGraphRoute")
    data object Profile: AppDestination("profileGraphRoute")
    data object Settings: AppDestination("settingsGraphRoute")
}