package io.nia.feature.foryou.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.nia.feature.foryou.ForYouScreen
import kotlinx.serialization.Serializable

@Serializable
data object ForYouRoute

fun NavController.navigateToForYou(navOptions: NavOptions) = navigate(route = ForYouRoute, navOptions = navOptions)

fun NavGraphBuilder.forYouScreen() {
    composable<ForYouRoute> {
        ForYouScreen()
    }
}
