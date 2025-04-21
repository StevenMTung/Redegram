package br.com.steventung.redegram.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.steventung.redegram.ui.components.RedegramBottomAppBar
import br.com.steventung.redegram.ui.components.bottomAppBarItems

@Composable
fun RedegramNavHost(navHostController: NavHostController) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    var isBottomAppBarVisible by rememberSaveable {
        mutableStateOf(true)
    }
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            if (isBottomAppBarVisible) {
                RedegramBottomAppBar(
                    items = bottomAppBarItems,
                    onItemsChanged = {
                        val route = it.destination.route
                        navHostController.navigate(route) {
                            launchSingleTop = true
                            popUpTo(route)
                        }
                    },
                    currentDestination = currentDestination?.route.toString()
                )
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
        ) {
            NavHost(
                navController = navHostController,
                startDestination = AppDestination.Home.route
            ) {
                homeGraph(
                    onHideBottomAppBar = {
                        isBottomAppBarVisible = false
                    },
                    onShowBottomAppBar = {
                        isBottomAppBarVisible = true
                    }
                )
                searchingGraph()
                addPostGraph()
                profileGraph()
                settingsGraph()
            }
        }
    }
}