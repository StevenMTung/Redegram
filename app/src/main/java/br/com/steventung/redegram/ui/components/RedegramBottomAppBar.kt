package br.com.steventung.redegram.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ControlPoint
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.steventung.redegram.navigation.AppDestination

class BottomAppBarItem(
    val icon: ImageVector,
    val destination: AppDestination
)

val bottomAppBarItems = listOf(
    BottomAppBarItem(
        icon = Icons.Filled.Home,
        destination = AppDestination.Home
    ),
    BottomAppBarItem(
        icon = Icons.Filled.Search,
        destination = AppDestination.Search
    ),
    BottomAppBarItem(
        icon = Icons.Filled.ControlPoint,
        destination = AppDestination.AddPost
    ),
    BottomAppBarItem(
        icon = Icons.Filled.PermIdentity,
        destination = AppDestination.Profile
    ),
    BottomAppBarItem(
        icon = Icons.Filled.Settings,
        destination = AppDestination.Settings
    )
)

@Composable
fun RedegramBottomAppBar(
    modifier: Modifier = Modifier,
    items: List<BottomAppBarItem>,
    onItemsChanged: (BottomAppBarItem) -> Unit = {},
    currentDestination: String = AppDestination.Home.route
) {
    NavigationBar(modifier = modifier) {
        items.forEach {
            val icon = it.icon
            val isSelected = it.destination.route == currentDestination
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(if (isSelected) 34.dp else 30.dp)
                    )
                },
                selected = isSelected,
                onClick = { onItemsChanged(it) },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Color.DarkGray,
                    unselectedTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RedegramBottomAppBarPreview() {
    RedegramBottomAppBar(
        items = bottomAppBarItems,
    )
}