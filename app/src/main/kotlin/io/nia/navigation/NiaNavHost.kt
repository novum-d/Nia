package io.nia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.nia.feature.bookmarks.navigation.bookmarksScreen
import io.nia.feature.foryou.navigation.ForYouBaseRoute
import io.nia.feature.foryou.navigation.forYouSection

@Composable
fun NiaNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ForYouBaseRoute,
    ) {
        forYouSection()
        bookmarksScreen()
    }
}