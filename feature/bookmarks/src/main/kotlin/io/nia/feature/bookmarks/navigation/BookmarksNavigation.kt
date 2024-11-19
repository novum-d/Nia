package io.nia.feature.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.nia.feature.bookmarks.BookmarksScreen
import kotlinx.serialization.Serializable

@Serializable
data object BookmarksRoute

fun NavController.navigateToBookmarks(navOptions: NavOptions) = navigate(route = BookmarksRoute, navOptions = navOptions)

fun NavGraphBuilder.bookmarksScreen() {
    composable<BookmarksRoute> {
        BookmarksScreen()
    }
}