package br.com.bit.guardian.app.ui.home

import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowWidthSizeClass
import br.com.bit.guardian.app.ui.destination.AppDestinations
import br.com.bit.guardian.app.ui.home.navbar.GuardianNavbar
import br.com.bit.guardian.app.ui.home.navbar.layout.GuardianBottomBarLayout
import br.com.bit.guardian.app.ui.home.navbar.layout.GuardianRailLayout
import br.com.bit.guardian.app.ui.home.navrail.GuardianNavRail

@Composable
fun GuardianNavigationSuiteScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (currentDestination: AppDestinations) -> Unit = {},
) {

    var currentDestination by rememberSaveable {
        mutableStateOf(AppDestinations.HOME)
    }

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val navigationType = with(adaptiveInfo) {
        if (windowPosture.isTabletop ||
            windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED ||
            windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM
        ) {
            NavigationSuiteType.NavigationRail
            NavigationSuiteType.NavigationRail
        } else {
            NavigationSuiteType.NavigationBar
        }
    }
    Surface {
        if (navigationType == NavigationSuiteType.NavigationRail) {
                GuardianRailLayout(
                    modifier = modifier,
                    navRail = {
                        GuardianNavRail(
                            initialDestination = currentDestination,
                            modifier = modifier,
                            entries = AppDestinations.entries
                        ) { currentDestination = it }
                    },
                    content = { content(currentDestination) }
                )
        } else {
            GuardianBottomBarLayout(
                modifier = modifier,
                bottomBar = {
                    GuardianNavbar(
                        modifier = Modifier,
                        entries = AppDestinations.entries
                    ) { currentDestination = it }
                },
                content = { content(currentDestination) }
            )
        }
    }
}
