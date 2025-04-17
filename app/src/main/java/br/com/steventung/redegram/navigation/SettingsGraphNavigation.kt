package br.com.steventung.redegram.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.steventung.redegram.ui.settings.SettingsScreen

fun NavGraphBuilder.settingsGraph(){
    composable(route = AppDestination.Settings.route){
        SettingsScreen()
    }
}