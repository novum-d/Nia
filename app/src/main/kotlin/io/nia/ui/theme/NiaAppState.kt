package io.nia.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.nia.navigation.TopLevelDestination

@Composable
fun rememberNiaAppState(
    navController: NavHostController = rememberNavController(),
): NiaAppState {
    return remember(
        navController,
    ) {
        NiaAppState(
            navController = navController,
        )
    }
}

@Stable
class NiaAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
}