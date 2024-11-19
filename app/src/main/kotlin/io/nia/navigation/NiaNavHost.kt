package io.nia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.nia.feature.bookmarks.navigation.bookmarksScreen
import io.nia.feature.foryou.navigation.ForYouRoute
import io.nia.feature.foryou.navigation.forYouScreen

@Composable
fun NiaNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ForYouRoute,
    ) {
        forYouScreen()
        bookmarksScreen()
    }
}