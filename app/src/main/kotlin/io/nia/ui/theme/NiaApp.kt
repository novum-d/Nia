package io.nia.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import io.nia.core.designsystem.component.NiaNavigationDefaults
import io.nia.navigation.NiaNavHost

@Composable
fun NiaApp(
    appState: NiaAppState,
) {
    NiaApp(appState) {}
}

@Composable
internal fun NiaApp(
    appState: NiaAppState,
    x: () -> Unit = {},
) {
    NiaNavigationSuiteScaffold(
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                item(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = destination.unselectedIcon,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null,
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) },
                    modifier = Modifier
                        .testTag("NiaNavItem")
                        .then(if (true) Modifier else Modifier)
                )
            }
        }
        // windowAdaptiveInfo = windowAdaptiveInfo,
    ) {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                NiaNavHost()
            }
        }
    }
}

class NiaNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

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
        content()
    }
}
