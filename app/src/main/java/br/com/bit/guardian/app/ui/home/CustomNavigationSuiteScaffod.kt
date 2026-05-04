package br.com.bit.guardian.app.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
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
import br.com.bit.guardian.core.designsystem.modifier.navigationBarPaddingOnly

@Composable
fun GuardianNavigationSuiteScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (currentDestination: AppDestinations) -> Unit = {}
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
        } else {
            NavigationSuiteType.NavigationBar
        }
    }
    Surface {
        if (navigationType == NavigationSuiteType.NavigationRail) {
            GuardianRailLayout(
                modifier = modifier.padding(
                    WindowInsets
                        .navigationBars
                        .only(WindowInsetsSides.End)
                        .asPaddingValues()
                ),
                navRail = {
                    GuardianNavRail(
                        initialDestination = currentDestination,
                        entries = AppDestinations.entries
                    ) { currentDestination = it }
                },
                content = {
                    content(currentDestination)
                }
            )
        } else {
            GuardianBottomBarLayout(modifier = modifier.navigationBarPaddingOnly(), bottomBar = {
                GuardianNavbar(
                    initialDestination = currentDestination,
                    entries = AppDestinations.entries
                ) { currentDestination = it }
            }, content = { content(currentDestination) })
        }
    }
}
