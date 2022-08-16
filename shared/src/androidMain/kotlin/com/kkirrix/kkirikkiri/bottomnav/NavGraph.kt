package com.kkirrix.kkirikkiri.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kkirrix.kkirikkiri.presentation.main.KRXMain

sealed class Direction(val route: String) {
    object Club : Direction("Club")
    object Search : Direction("Search")
    object Profile : Direction("Profile")
}

@Composable
fun BottomNavContent(component: KRXMain) {
    val navController = rememberNavController()
    val items = listOf(
        Icons.Default.Home to Direction.Club.route,
        Icons.Default.Search to Direction.Search.route,
        Icons.Default.Person to Direction.Profile.route
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                fun navigate(route: String) = navController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }

                items.forEach { (icon, route) ->
                    BottomNavigationItem(
                        selected = currentRoute == route,
                        onClick = { navigate(route) },
                        icon = { Icon(imageVector = icon, contentDescription = null) }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Direction.Search.route
        ) {
            addComposableDestinations(component)
        }
    }
}

private fun NavGraphBuilder.addComposableDestinations(component: KRXMain) {
    composable(Direction.Club.route) {
        Text("Club")
    }
    composable(Direction.Search.route) {
        Text("Search")
    }
    composable(Direction.Profile.route) {
        Text("Profile")
    }
}