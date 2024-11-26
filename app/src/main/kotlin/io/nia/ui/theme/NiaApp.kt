package io.nia.ui.theme

import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import io.nia.core.designsystem.component.NiaNavigationDefaults
import io.nia.navigation.NiaNavHost

@Composable
fun NiaApp(
    appState: NiaAppState,
) {
    NiaApp(appState)
}

@Composable
internal fun NiaApp(
    appState: NiaAppState,
    x: () -> Unit = {},
) {
    NiaNavHost()
}

class NiaNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
)

@Composable
fun NiaNavigationSuiteScaffold(
    navigationSuiteItems: NiaNavigationSuiteScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
            selectedTextColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
            indicatorColor = NiaNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
            selectedTextColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
            indicatorColor = NiaNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
            selectedTextColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
        ),
    )
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            NiaNavigationSuiteScope(
                this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        }
    ) {
    }
}
