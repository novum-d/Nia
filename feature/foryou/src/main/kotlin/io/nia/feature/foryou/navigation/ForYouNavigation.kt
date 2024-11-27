package io.nia.feature.foryou.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.nia.feature.foryou.ForYouScreen
import kotlinx.serialization.Serializable

@Serializable
data object ForYouRoute

@Serializable
data object ForYouBaseRoute

fun NavController.navigateToForYou(navOptions: NavOptions) = navigate(route = ForYouRoute, navOptions = navOptions)

fun NavGraphBuilder.forYouSection() {
    navigation<ForYouBaseRoute>(startDestination = ForYouRoute) {
        composable<ForYouRoute> {
            ForYouScreen()
        }
    }
}
