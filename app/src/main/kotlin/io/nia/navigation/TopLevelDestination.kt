package io.nia.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import io.nia.core.designsystem.icon.NiaIcons
import io.nia.feature.bookmarks.navigation.BookmarksRoute
import io.nia.feature.foryou.R
import io.nia.feature.foryou.navigation.ForYouBaseRoute
import io.nia.feature.foryou.navigation.ForYouRoute
import kotlin.reflect.KClass


enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    FOR_YOU(
        selectedIcon = NiaIcons.Upcoming,
        unselectedIcon = NiaIcons.UpcomingBorder,
        iconTextId = R.string.feature_foryou_title,
        titleTextId = io.nia.R.string.app_name,
        route = ForYouRoute::class,
        baseRoute = ForYouBaseRoute::class,
    ),
    BOOKMARKS(
        selectedIcon = NiaIcons.Bookmarks,
        unselectedIcon = NiaIcons.BookmarksBorder,
        iconTextId = io.nia.feature.bookmarks.R.string.feature_bookmarks_title,
        titleTextId = io.nia.feature.bookmarks.R.string.feature_bookmarks_title,
        route = BookmarksRoute::class,
    ),
    // INTERESTS(
    //     selectedIcon = NiaIcons.Grid3x3,
    //     unselectedIcon = NiaIcons.Grid3x3,
    //     iconTextId = searchR.string.feature_search_interests,
    //     titleTextId = searchR.string.feature_search_interests,
    //     route = InterestsRoute::class,
    // ),
}
