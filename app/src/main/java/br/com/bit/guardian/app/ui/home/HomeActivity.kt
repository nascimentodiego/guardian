package br.com.bit.guardian.app.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.app.ui.destination.ActivitiesDestination
import br.com.bit.guardian.app.ui.destination.AppDestinations
import br.com.bit.guardian.app.ui.destination.DeviceDestination
import br.com.bit.guardian.app.ui.destination.HomeDestination
import br.com.bit.guardian.app.ui.destination.SettingDestination
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.app.ui.home.navbar.GuardianNavbar
import br.com.bit.guardian.app.ui.home.navbar.layout.GuardianMainLayout
import br.com.bit.guardian.app.ui.home.navrail.GuardianNavRail

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            HomeNavigationBarContent()
//            HomeContent
            GuardianTheme {
                var currentDestination by rememberSaveable {
                    mutableStateOf(AppDestinations.HOME)
                }

                Scaffold { innerPadding ->
                    innerPadding.calculateTopPadding()
                     GuardianNavRail(
                         initialDestination = currentDestination,
                         modifier = Modifier,
                         entries = AppDestinations.entries
                     ) { destination ->
                         currentDestination = destination
                         Log.d("Guardian", destination.name)
                     }

/*                    GuardianTheme(
                        isStatusBarTranslucent = false
                    ) {
                        Surface {
                            var currentDestination by rememberSaveable {
                                mutableStateOf(AppDestinations.HOME)
                            }

                            GuardianMainLayout(
                                bottomBar = {
                                    GuardianNavbar(
                                        modifier = Modifier,
                                        entries = AppDestinations.entries
                                    ) { destination ->
                                        currentDestination = destination
                                        Log.d("Guardian", destination.name)
                                    }
                                },
                                content = {
                                    when (currentDestination) {
                                        AppDestinations.HOME -> HomeDestination()
                                        AppDestinations.DEVICES -> DeviceDestination()
                                        AppDestinations.ACTIVITIES -> ActivitiesDestination()
                                        AppDestinations.SETTINGS -> SettingDestination()
                                    }
                                }
                            )
                        }
                    }*/

                }
            }
        }
    }
}

@Composable
private fun MyNavigationBar() {
    Surface(
        color = Color.Transparent,
        contentColor = GuardianTheme.colors.primary,
        tonalElevation = NavigationBarDefaults.Elevation,
        modifier = Modifier
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .defaultMinSize(minHeight = 80.0.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                AppDestinations.entries.forEach {
                    Column {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(text = stringResource(id = it.label))
                    }
                }
            }
        )
    }
}

@Composable
private fun NavigationItemIcon(
    icon: @Composable () -> Unit,
    badge: (@Composable () -> Unit)? = null,
) {
    if (badge != null) {
        BadgedBox(badge = { badge.invoke() }) { icon() }
    } else {
        icon()
    }
}


@Composable
fun HomeContent() {

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType = with(adaptiveInfo) {

        NavigationSuiteType.NavigationBar

//            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
//                NavigationSuiteType.NavigationDrawer
//            } else {
//                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
//            }
    }

    val myNavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = GuardianTheme.colors.primaryContainer,
            selectedIconColor = GuardianTheme.colors.onPrimaryContainer
        ),
    )

    CustomNavigationSuiteScaffold(
        layoutType = customNavSuiteType,
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(id = it.icon),
                            contentDescription = stringResource(it.contentDescription)
                        )
                    },
                    label = { Text(stringResource(it.label)) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it },
                    colors = myNavigationSuiteItemColors
                )
            }
        },
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = Color.Transparent
        )
    ) {
        // Destination content.
        when (currentDestination) {
            AppDestinations.HOME -> HomeDestination()
            AppDestinations.DEVICES -> DeviceDestination()
            AppDestinations.ACTIVITIES -> ActivitiesDestination()
            AppDestinations.SETTINGS -> SettingDestination()
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    GuardianTheme {
        HomeContent()
    }
}

