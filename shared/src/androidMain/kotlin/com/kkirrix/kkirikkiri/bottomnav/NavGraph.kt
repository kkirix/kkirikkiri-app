package com.kkirrix.kkirikkiri.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kkirrix.kkirikkiri.category.categoryGraph
import com.kkirrix.kkirikkiri.gathering.gatheringGraph
import com.kkirrix.kkirikkiri.presentation.main.KRXMain

sealed class Direction(val route: String) {
    object Category: Direction("Category")
    object Gathering : Direction("Gathering")
    object Search : Direction("Search")
    object Profile : Direction("Profile")
}

@Composable
fun BottomNavContent(component: KRXMain) {
    val navController = rememberNavController()
    val items = listOf(
        Icons.Default.List to Direction.Category.route,
        Icons.Default.Home to Direction.Gathering.route,
        Icons.Default.Search to Direction.Search.route,
        Icons.Default.Person to Direction.Profile.route
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { (icon, route) ->
                    BottomNavigationItem(
                        selected = currentRoute == route,
                        onClick = { navigate(navController, route) },
                        icon = { Icon(imageVector = icon, contentDescription = null) }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Direction.Category.route
        ) {
            addComposableDestinations(component)
        }
    }
}

private fun NavGraphBuilder.addComposableDestinations(component: KRXMain) {
    categoryGraph(component.categoryChildStack)
    gatheringGraph(component.gatheringChildStack)
    composable(Direction.Search.route) {
        Text("Search")
    }
    composable(Direction.Profile.route) {
        Text("Profile")
    }
}

private fun navigate(navController: NavController, route: String) = navController.navigate(route) {
    launchSingleTop = true
    restoreState = true
    popUpTo(navController.currentDestination?.id?: navController.graph.startDestinationId) {
        inclusive = true
        saveState = true
    }
}